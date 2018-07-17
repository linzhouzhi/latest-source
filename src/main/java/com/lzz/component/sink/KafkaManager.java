package com.lzz.component.sink;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gl49 on 2018/7/17.
 */
public class KafkaManager {
    private int producerSize;
    private String topic;
    private String brokers;
    Map<Integer, KafkaProducer> kafkaProducerMap = new ConcurrentHashMap<>();

    public KafkaManager(String brokers, String topic, int producerSize){
        this.brokers = brokers;
        this.topic = topic;
        this.producerSize = producerSize;
    }

    public void producer(String msg){
        int hashCode = msg.hashCode();
        int index = hashCode % producerSize;
        KafkaProducer producer = kafkaProducerMap.get(index);
        if(  null == producer ){
            producer = new KafkaProducer(brokers, topic);
            kafkaProducerMap.put( index, producer );
        }
        producer.append( msg );
    }

    public void closeAll(){
        for(Map.Entry<Integer, KafkaProducer> producerItem : kafkaProducerMap.entrySet()){
            KafkaProducer producer = producerItem.getValue();
            producer.close();
        }
    }
}
