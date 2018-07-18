package com.lzz.component.source.hbase;

import com.lzz.app.model.MetaInfo;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by gl49 on 2018/6/23.
 */
public class ScanRowKeyTask implements Callable<ScanResult> {
    public static final Log logger = LogFactory.getLog(ScanRowKeyTask.class);
    private long MINUTE_VALUE = 60 * 1000;
    private Set<String> rowKeySet = new HashSet<>(10000);
    private MetaInfo metaInfo;
    private Table table;
    private Pair<byte[], byte[]> pair;
    private long lastEndTime = 0;
    private long thisStartTime = 0;
    public ScanRowKeyTask(Connection connection, Pair<byte[], byte[]> pair, MetaInfo metaInfo, long lastEndTime, long thisStartTime) throws IOException {
        this.metaInfo = metaInfo;
        this.table = connection.getTable(TableName.valueOf( metaInfo.getTableName() ));
        this.pair = pair;
        this.lastEndTime = lastEndTime;
        this.thisStartTime = thisStartTime;
    }

    @Override
    public ScanResult call() throws Exception {
        Boolean success= false;
        try {
            rowKeySet = getRowKeySet(table, pair, lastEndTime,thisStartTime);
            success=true;
        }catch (Exception e){
            logger.error( e );

        }finally {
            this.table.close();
        }
        return new ScanResult(success,rowKeySet);
    }

    public  Set<String> getRowKeySet(Table table, Pair<byte[], byte[]> pair, long startTime, long endTime) throws IOException {
        Set<String> rowKeySet = new HashSet<>(100000);
        Scan scan = new Scan();
        scan.setStartRow(pair.getFirst());
        scan.setStopRow(pair.getSecond());

        if( startTime == 0) { // 如果第一次运行，那么就是运行最大允许范围内时间
            startTime = endTime - this.metaInfo.getMaxUpdateRangeTime() * MINUTE_VALUE;
        }else if( (endTime - startTime) > this.metaInfo.getMaxUpdateRangeTime() * MINUTE_VALUE ){ //如果当前时间距离上一次时间超过设置的值，那么开始时间
            logger.error("endTime and startTime is too long range:" + (endTime - startTime) + " and starTime:" + startTime + " change " + (endTime - this.metaInfo.getMaxUpdateRangeTime() * MINUTE_VALUE));
            startTime = endTime - this.metaInfo.getMaxUpdateRangeTime();
        }
        logger.info( "endTime:" + endTime + " starTime: " + startTime);
        scan.setTimeRange( startTime, endTime );
        scan.setCaching(1000);
        Boolean success =false;
        int retry= 0;
        while(!success && retry < 3 ){
            retry++;
            try{
                ResultScanner resultScanner = table.getScanner(scan);
                Iterator<Result> iterator = resultScanner.iterator();
                int count =0;
                while (iterator.hasNext()) {
                    try {
                        Result result = iterator.next();
                        rowKeySet.add( Bytes.toString(result.getRow()) );
                    }catch (Exception e){
                        logger.error( "error iterator next:" + e );
                        count++;
                        if(count>10){
                            throw new RuntimeException("fail 10 times in iterator next",e);
                        }
                    }
                }
                success = true;
            }catch (Exception e){
                logger.error("error in scan ", e);
            }
        }
        if(!success){
            throw new RuntimeException("fail after retry");
        }
        return rowKeySet;
    }
}
