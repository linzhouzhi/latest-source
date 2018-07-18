package com.lzz.component.channel;

import com.lzz.app.model.MetaInfo;
import com.lzz.component.consumer.CacheConsumer;
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
            queue = new LinkedBlockingDeque<>(200000);
            queueMap.put(metaId, queue);
            // 启动消费线程处理
            threedPool.submit(new CacheConsumer(queue, metaInfo));
        }
        return queueMap.get( metaId );
    }

    public static void removeQueue(String key){
        queueMap.remove( key );
    }
}
