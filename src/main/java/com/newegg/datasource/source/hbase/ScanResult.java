package com.newegg.datasource.source.hbase;

/**
 * Created by gl49 on 2018/6/23.
 */
import java.util.Set;

public class  ScanResult{
    private Boolean success;
    private Set<String> rowKeySet;

    public ScanResult(Boolean success, Set<String> rowKeySet) {
        this.success = success;
        this.rowKeySet = rowKeySet;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Set<String> getRowKeySet() {
        return rowKeySet;
    }
}
