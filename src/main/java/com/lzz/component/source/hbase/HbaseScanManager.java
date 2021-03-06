package com.lzz.component.source.hbase;

import com.lzz.app.model.MetaInfo;
import com.lzz.app.util.DateUtil;
import com.lzz.component.channel.QueueCache;
import com.lzz.component.source.UpdateDataTask;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Pair;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gl49 on 2018/6/23.
 */
@Component
public class HbaseScanManager implements UpdateDataTask {
    public static long MAX_UPDATE_RANGE_TIME = 10*60*1000;
    public static final Log logger = LogFactory.getLog(HbaseScanManager.class);
    public static ExecutorService threadPool = Executors.newFixedThreadPool(500);
    private static Map<String, AtomicLong> endTimeMap = new ConcurrentHashMap<>();
    private MetaInfo metaInfo;
    public HbaseScanManager(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    @Override
    public Boolean scanUpdate() {
        System.out.println("start ...................................." + metaInfo.getTableName() );
        QueueCache queueCache = new QueueCache(this.metaInfo);
        BlockingQueue<String> queue = queueCache.queue();

        logger.info( "EndTime: " + getEndTime(String.valueOf(this.metaInfo.getId()))  );
        Set<String> rowKeySet = new HashSet<>(100000); //要重新初始化
        Connection connection = null;
        boolean success=true;
        try {
            long now = DateUtil.current() - 0; //往前推一分钟，避免hbase 所在服务器的时间跟本机时间一致
            long lastEndTime = getEndTime(String.valueOf(this.metaInfo.getId())).get();
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            connection = HbaseManager.getHbaseConnection(metaInfo);
            logger.info("start get region");
            List<Pair<byte[],byte[]>> pairList = HbaseManager.getPairList(connection, metaInfo.getTableName());
            logger.info("region size : " + pairList.size() + " and use time:" + stopWatch.getTime() );

            long  startTime = lastEndTime;
            long  endTime = now;
            if( startTime == 0) { // 如果第一次运行，那么就是运行周期范围内的几分钟
                startTime = now - MAX_UPDATE_RANGE_TIME;
            }else if( (endTime - startTime) > 120*60*1000 ){ // 持续超过两个小时,那么就使用最近十分钟的
                logger.error("endTime and startTime is too long range:" + (endTime - startTime));
                startTime = now - MAX_UPDATE_RANGE_TIME;
            }else if( (endTime - startTime) > MAX_UPDATE_RANGE_TIME ){ //如果当前时间距离上一次scan完成的时间，超过最大范围时间10分钟，那么这个任务的结束时间是上个任务往前推十分钟
                logger.error("last end time > " + MAX_UPDATE_RANGE_TIME);
                endTime = lastEndTime + MAX_UPDATE_RANGE_TIME;
            }
            logger.info("start scan startTime:" + startTime + "  endTime:" + endTime);

            List<Future<ScanResult>> futureList = new ArrayList<>();
            for(Pair<byte[], byte[]> pair: pairList){
                Future<ScanResult> futureResSet = threadPool.submit( new ScanRowKeyTask(connection, pair, this.metaInfo, startTime, endTime) );
                futureList.add( futureResSet );
            }
            logger.info("add all task key to rowKeySet");
            for(Future<ScanResult> f : futureList){
                ScanResult scanResult = f.get();
                if( !scanResult.getSuccess()){
                    success=false;
                }
                rowKeySet.addAll( scanResult.getRowKeySet() );
            }
            stopWatch.stop();
            logger.info("stop scan update row key and use time:" + stopWatch.getTime() + " and all rokeyset size" + rowKeySet.size());
            for(String rowkey : rowKeySet){
                queue.put(rowkey);
            }
            // 中间某一个过程抛异常不会走到这一步
            if(success){
                setEndTime( String.valueOf(this.metaInfo.getId()), now );
            }
            logger.info("current task issuccess:" + success);
        }catch (Exception e){
            logger.error( e );
        }finally {
            if( null != connection ){
                try {
                    connection.close();
                } catch (IOException e) {
                    logger.error( e );
                }
            }
        }
        return success;
    }

    private void setEndTime(String id, long now) {
        AtomicLong endTime = endTimeMap.get( id );
        endTime.set(now);
    }

    private AtomicLong getEndTime(String id) {
        AtomicLong endTime = endTimeMap.get( id );
        if( null == endTime || endTime.get() == 0 ){
            endTimeMap.put(id, new AtomicLong(0));
        }
        return endTimeMap.get(id);
    }
}
