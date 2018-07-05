package com.newegg.app.model;

import io.grpc.distribute.DmetaParam;

/**
 * Created by gl49 on 2018/6/23.
 */
public class MetaInfo extends DmetaParam {
    private int id;
    private String tableName;
    private String zkAddress;
    private String kafkaAddress;
    private String kfakaTopic;
    private long maxUpdateRangeTime;
    private long updateRangeTime;

    public MetaInfo(int id, String tableName, String zkAddress, String kafkaAddress, String kfakaTopic, long maxUpdateRangeTime, long updateRangeTime) {
        this.id = id;
        this.tableName = tableName;
        this.zkAddress = zkAddress;
        this.kafkaAddress = kafkaAddress;
        this.kfakaTopic = kfakaTopic;
        this.maxUpdateRangeTime = maxUpdateRangeTime;
        this.updateRangeTime = updateRangeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getKafkaAddress() {
        return kafkaAddress;
    }

    public void setKafkaAddress(String kafkaAddress) {
        this.kafkaAddress = kafkaAddress;
    }

    public String getKfakaTopic() {
        return kfakaTopic;
    }

    public void setKfakaTopic(String kfakaTopic) {
        this.kfakaTopic = kfakaTopic;
    }

    public long getMaxUpdateRangeTime() {
        return maxUpdateRangeTime;
    }

    public void setMaxUpdateRangeTime(long maxUpdateRangeTime) {
        this.maxUpdateRangeTime = maxUpdateRangeTime;
    }

    public long getUpdateRangeTime() {
        return updateRangeTime;
    }

    public void setUpdateRangeTime(long updateRangeTime) {
        this.updateRangeTime = updateRangeTime;
    }
}
