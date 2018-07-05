package com.newegg.datasource.source.hbase;

import com.newegg.app.model.MetaInfo;
import io.grpc.distribute.DCallable;
import io.grpc.distribute.DmetaParam;

/**
 * Created by gl49 on 2018/7/5.
 */
public class TmpTask extends DCallable {
    MetaInfo metaInfo;
    public TmpTask(DmetaParam metaInfo) {
        this.metaInfo = (MetaInfo) metaInfo;
    }
    @Override
    protected Object call() {
        UpdateDataTask hbaseScanTask = new HbaseScanTask( metaInfo );
        hbaseScanTask.scanUpdate();
        return new Boolean(true);
    }
}