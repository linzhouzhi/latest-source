package com.lzz.component.source.hbase;

import com.lzz.app.model.MetaInfo;
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
    public static final Log logger = LogFactory.getLog(HbaseScanManager.class);
    public static ExecutorService threadPool = Executors.newFixedThreadPool(500);
    private static Map<String, AtomicLong> endTimeMap = new ConcurrentHashMap<>();
    private MetaInfo metaInfo;
    public HbaseScanManager(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    @Override
    public Boolean scanUpdate() {
        QueueCache queueCache = new QueueCache(this.metaInfo);
        BlockingQueue<String> queue = queueCache.queue();

        logger.info( "itemPrice EndTime: " + getEndTime(String.valueOf(this.metaInfo.getId()))  );
        Set<String> rowKeySet = new HashSet<>(100000); //要重新初始化
        Connection connection = null;
        boolean success=true;
        try {
            long now = System.currentTimeMillis() - 15*60*1000; //往前推一分钟，避免hbase 所在服务器的时间跟本机时间一致
            long lastEndTime = getEndTime(String.valueOf(this.metaInfo.getId())).get();
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            connection = getHbaseConnection(metaInfo);
            logger.info("start get region");
            List<Pair<byte[],byte[]>> pairList = getPairList(connection, "ecitem:IM_ItemPrice");
            logger.info("region size : " + pairList.size() + " and use time:" + stopWatch.getTime() );

            List<Future<ScanResult>> futureList = new ArrayList<>();
            for(Pair<byte[], byte[]> pair: pairList){
                Future<ScanResult> futureResSet = threadPool.submit( new ScanRowKeyTask(connection, pair, this.metaInfo, lastEndTime, now) );
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
            rowKeySet.add("ffffhahaha aa");
            for(String rowkey : rowKeySet){
                queue.put(rowkey);
            }
            // 中间某一个过程抛异常不会走到这一步
            if(success){
                logger.info( "task startTime: " + getEndTime(String.valueOf(this.metaInfo.getId())) + "and endTime:" + now );
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
        if( null == endTime ){
            endTimeMap.put(id, new AtomicLong(0));
        }
        return endTimeMap.get(id);
    }

    public static Connection getHbaseConnection(MetaInfo metaInfo) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum",  "10.16.46.193:2181" );
        configuration.set("hbase.client.retries.number", "3");
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    public static List<Pair<byte[],byte[]>> getPairList(Connection connection, String tableName) throws IOException {
        List<HRegionLocation> hRegionLocationList= connection.getRegionLocator(TableName.valueOf( tableName )).getAllRegionLocations();
        List<Pair<byte[],byte[]>> pairList = new LinkedList<>();
        for(HRegionLocation hRegionLocation:hRegionLocationList){
            HRegionInfo region = hRegionLocation.getRegionInfo();
            byte[] startKeys = region.getStartKey();
            byte[] endKeys = region.getEndKey();
            pairList.add(new Pair<>(startKeys,endKeys));
        }
        return pairList;
    }
}
