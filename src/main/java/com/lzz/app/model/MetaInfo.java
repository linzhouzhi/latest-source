package com.lzz.app.model;

import org.springframework.stereotype.Component;
import util.dconcurrent.DmetaParam;

/**
 * Created by gl49 on 2018/6/23.
 */
@Component
public class MetaInfo extends DmetaParam {
    private int id;
    private String incObjectName;
    private String tableName;
    private String familyColumn;
    private String zkAddress;
    private String kafkaAddress;
    private String kfakaTopic;
    private int maxUpdateRangeTime;
    private int updateRangeTime;
    private int startSwitch;
    private long updateTime;

    public MetaInfo(){

    }

    public MetaInfo(String incObjectName, String tableName, String familyColumn, String zkAddress, String kafkaAddress, String kfakaTopic, int maxUpdateRangeTime, int updateRangeTime, int startSwitch, long updateTime) {
        this.incObjectName = incObjectName;
        this.tableName = tableName;
        this.familyColumn = familyColumn;
        this.zkAddress = zkAddress;
        this.kafkaAddress = kafkaAddress;
        this.kfakaTopic = kfakaTopic;
        this.maxUpdateRangeTime = maxUpdateRangeTime;
        this.updateRangeTime = updateRangeTime;
        this.startSwitch = startSwitch;
        this.updateTime = updateTime;
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

    public int getMaxUpdateRangeTime() {
        return maxUpdateRangeTime;
    }

    public void setMaxUpdateRangeTime(int maxUpdateRangeTime) {
        this.maxUpdateRangeTime = maxUpdateRangeTime;
    }

    public int getUpdateRangeTime() {
        return updateRangeTime;
    }

    public void setUpdateRangeTime(int updateRangeTime) {
        this.updateRangeTime = updateRangeTime;
    }

    public int getStartSwitch() {
        return startSwitch;
    }

    public void setStartSwitch(int startSwitch) {
        this.startSwitch = startSwitch;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MetaInfo{" +
                "id=" + id +
                ", incObjectName='" + incObjectName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", familyColumn='" + familyColumn + '\'' +
                ", zkAddress='" + zkAddress + '\'' +
                ", kafkaAddress='" + kafkaAddress + '\'' +
                ", kfakaTopic='" + kfakaTopic + '\'' +
                ", maxUpdateRangeTime=" + maxUpdateRangeTime +
                ", updateRangeTime=" + updateRangeTime +
                ", startSwitch=" + startSwitch +
                ", updateTime=" + updateTime +
                '}';
    }
}
