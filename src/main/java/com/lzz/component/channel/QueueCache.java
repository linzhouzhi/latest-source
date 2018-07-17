package com.lzz.component.channel;

import com.lzz.app.logic.MetaLogic;
import com.lzz.app.model.MetaInfo;
import com.lzz.component.sink.KafkaManager;
import com.lzz.component.sink.KafkaProducer;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by gl49 on 2018/6/23.
 */
@Component
public class QueueCache {
    private static ExecutorService threedPool = Executors.newCachedThreadPool();
    private static final Map<String, BlockingDeque<String>> queueMap = new ConcurrentHashMap();
    private MetaInfo metaInfo;
    public QueueCache(MetaInfo metaInfo){
        this.metaInfo = metaInfo;
    }

    public BlockingQueue<String> queue(){
        String metaId = String.valueOf(this.metaInfo.getId());
        BlockingDeque<String> queue = queueMap.get( metaId );
        if( null == queue ){
            queueMap.put(metaId, new LinkedBlockingDeque<>(200000));
            // 启动消费线程处理
            threedPool.submit(new CacheConsumer(metaInfo));
        }
        return queueMap.get( metaId );
    }

    private class CacheConsumer implements Runnable {
        MetaInfo metaInfo;
        public CacheConsumer(MetaInfo metaInfo) {
            this.metaInfo = metaInfo;
        }
        @Override
        public void run() {
            BlockingQueue<String> blockingQueue = queue();
            String brokerStr = MetaLogic.getKafkaBrokerList( metaInfo.getKafkaAddress() );
            KafkaManager kafkaManager = new KafkaManager(brokerStr, metaInfo.getKfakaTopic(), 10);
            try {
                while (true){
                    String msg = blockingQueue.poll(10, TimeUnit.MINUTES);
                    if( null == msg ){
                        throw  new RuntimeException("msg blockding is long time empty");
                    }
                    kafkaManager.producer(msg );
                }
            }catch (Exception e){
                System.out.println( "remove queue....." );
            }finally {
                // 如果走出了这一行，说明队列在约定时间内为空
                queueMap.remove( String.valueOf(this.metaInfo.getId()) );
                kafkaManager.closeAll();
            }
        }
    }
}
