package com.lzz.component.consumer;

import com.lzz.app.logic.MetaLogic;
import com.lzz.app.model.MetaInfo;
import com.lzz.component.channel.QueueCache;
import com.lzz.component.sink.KafkaManager;
import com.lzz.component.source.hbase.HbaseManager;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by gl49 on 2018/7/18.
 */
public class CacheConsumer implements Runnable {
    private static Gson gson = new Gson();
    public static int HBASE_BATCH_SIZE = 100;
    private MetaInfo metaInfo;
    private BlockingDeque<String> queue;

    public CacheConsumer(BlockingDeque<String> queue, MetaInfo metaInfo) {
        this.queue = queue;
        this.metaInfo = metaInfo;
    }

    @Override
    public void run() {
        String brokerStr = MetaLogic.getKafkaBrokerList( metaInfo.getKafkaAddress() );
        KafkaManager kafkaManager = new KafkaManager(brokerStr, metaInfo.getKfakaTopic(), 10);
        try {
            Connection connection = HbaseManager.getHbaseConnection(metaInfo);
            Table table = connection.getTable( TableName.valueOf(metaInfo.getTableName()) );
            List<Get> getList = new ArrayList<>();
            int count = 0;
            int emptyQueuePollCount = 0;
            while (true){
                String rowkey = this.queue.poll(60, TimeUnit.SECONDS);
                if( null == rowkey ){
                    if( emptyQueuePollCount++ > 10 ){  // 连续 10 次拿不到数据就直接退出了
                        throw  new RuntimeException("msg blockding is long time empty");
                    }
                }else{
                    emptyQueuePollCount = 0;
                }
                // 如果队列中有数据，并且 count 小于批次处理的大小
                if( emptyQueuePollCount == 0 && count < HBASE_BATCH_SIZE ){
                    Get get = new Get( rowkey.getBytes() );
                    formatGet(get, metaInfo.getFamilyColumn());
                    getList.add( get );
                    count++;
                }else{
                    if( !getList.isEmpty() ){
                        Result[] resList = table.get( getList );
                        for(Result hbaseRes : resList ){
                            String formatKey = formatHbaseResult(metaInfo.getFamilyColumn(), hbaseRes);
                            System.out.println( formatKey + "------------------- formatkey");
                            kafkaManager.producer( formatKey );
                        }
                    }
                    count = 0;
                    getList = new ArrayList<>();
                }
            }
        }catch (Exception e){
            System.out.println( "remove queue....." );
        }finally {
            // 如果走出了这一行，说明队列在约定时间内为空
            QueueCache.removeQueue( String.valueOf(this.metaInfo.getId()) );
            kafkaManager.closeAll();
        }
    }

    private String formatHbaseResult(String familyColumn, Result hbaseRes) {
        Map<String, String> resFormatMap = new HashMap<>();
        String[] fcArrStr = familyColumn.split(",");
        for(String fcStrItem : fcArrStr){
            String[] fcItem = fcStrItem.split(":");
            if( fcItem.length == 2 ){
                byte[] family = Bytes.toBytes(fcItem[0]);
                byte[] columns = Bytes.toBytes(fcItem[1]);
                String formatKey = Bytes.toString( hbaseRes.getValue(family, columns) );
                resFormatMap.put( fcStrItem, formatKey );
            }
        }
        return gson.toJson( resFormatMap );
    }

    private void formatGet(Get get, String familyColumn) {
        String[] fcArrStr = familyColumn.split(",");
        for(String fcStrItem : fcArrStr){
            String[] fcItem = fcStrItem.split(":");
            if( fcItem.length == 2 ){
                get.addColumn( Bytes.toBytes(fcItem[0]), Bytes.toBytes(fcItem[1]) );
            }
        }
    }
}