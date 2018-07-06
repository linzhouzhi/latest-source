package com.lzz.datasource.channel;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by gl49 on 2018/6/23.
 */
public class QueueCache {
    private static final Map<String, BlockingDeque<String>> queueMap = new ConcurrentHashMap();

    public static BlockingQueue<String> queue(String key){
        BlockingDeque<String> queue = queueMap.get( key );
        if( null == queue ){
            queueMap.put(key, new LinkedBlockingDeque<>(200000));
        }
        return queueMap.get( key );
    }

}
