package com.lzz.component.source.hbase;

import com.lzz.app.model.MetaInfo;
import com.lzz.component.source.UpdateDataTask;
import org.springframework.stereotype.Component;
import util.dconcurrent.DCallable;

/**
 * Created by gl49 on 2018/7/17.
 */
@Component
public class UpdateHbaseTask extends DCallable<Boolean> {
    private MetaInfo metaInfo;
    public UpdateHbaseTask(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
        }

    @Override
    protected Boolean call() {
        UpdateDataTask hbaseScanTask = new HbaseScanManager( metaInfo );
        Boolean updateRes = hbaseScanTask.scanUpdate();
        return updateRes;
    }
}
