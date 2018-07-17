package com.lzz.component.channel;

import com.lzz.app.model.MetaInfo;
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
            System.out.println( metaInfo + "--------------------- queue");
            KafkaProducer kafkaProducer = new KafkaProducer("ssecbigdata07:9093", "test001");
            while (true){
                try {
                    String msg = blockingQueue.take();
                    System.out.println( msg );
                    kafkaProducer.append( msg );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
