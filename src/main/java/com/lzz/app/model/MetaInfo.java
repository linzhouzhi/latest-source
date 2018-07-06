package com.lzz.app.model;

import io.grpc.distribute.DmetaParam;

/**
 * Created by gl49 on 2018/6/23.
 */
public class MetaInfo extends DmetaParam {
    private int id;
    private String incObjectName;
    private String tableName;
    private String familyColumn;
    private String zkAddress;
    private String kafkaAddress;
    private String kfakaTopic;
    private long maxUpdateRangeTime;
    private long updateRangeTime;

    public MetaInfo(){

    }
    public MetaInfo(int id, String incObjectName, String tableName, String familyColumn, String zkAddress, String kafkaAddress, String kfakaTopic, long maxUpdateRangeTime, long updateRangeTime) {
        this.id = id;
        this.incObjectName = incObjectName;
        this.tableName = tableName;
        this.familyColumn = familyColumn;
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

    public String getIncObjectName() {
        return incObjectName;
    }

    public void setIncObjectName(String incObjectName) {
        this.incObjectName = incObjectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFamilyColumn() {
        return familyColumn;
    }

    public void setFamilyColumn(String familyColumn) {
        this.familyColumn = familyColumn;
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
