package com.lzz.datasource.source;

import com.lzz.app.model.MetaInfo;
import com.lzz.datasource.source.hbase.HbaseScanTask;
import com.lzz.datasource.source.hbase.UpdateDataTask;

/**
 * Created by gl49 on 2018/6/23.
 */
public class ScanSchedule {
    public static void main(String[] args){
        MetaInfo metaInfo = new MetaInfo();
        UpdateDataTask hbaseScanTask = new HbaseScanTask( metaInfo );
        hbaseScanTask.scanUpdate();
    }
}
