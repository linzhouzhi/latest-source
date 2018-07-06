package com.lzz.app.logic;

import com.lzz.app.dao.IMetaInfoDao;
import com.lzz.app.model.MetaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by gl49 on 2018/7/6.
 */
@Component
public class MetaLogic {
    @Autowired
    private IMetaInfoDao metaInfoDao;

    public List<MetaInfo> getMetaInfoList() {
        return  metaInfoDao.getMetaInfoList();
    }

    public MetaInfo getMetaInfo(int id) {
        return metaInfoDao.getMetaInfo( id );
    }

    public boolean removeMetaInfo(int id) {
        int i = metaInfoDao.removeMetaInfo(id);
        if( i < 0 ){
            return false;
        }
        return true;
    }


    public boolean addMetaInfo(MetaInfo metaInfo) {
        int i = metaInfoDao.addMetaInfo( metaInfo );
        if( i <= 0 ){
            return false;
        }
        return true;
    }

    public boolean updateStartSwitch(int id, int startSwitch) {
        return metaInfoDao.updateStartSwitch(id, startSwitch);
    }
}
