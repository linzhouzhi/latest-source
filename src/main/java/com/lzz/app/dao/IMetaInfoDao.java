package com.lzz.app.dao;

import com.lzz.app.model.MetaInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gl49 on 2018/7/6.
 */
@Repository
public interface IMetaInfoDao {
    List<MetaInfo> getMetaInfoList();

    List<MetaInfo> getMetaInfoStartSwitchList();

    MetaInfo getMetaInfo(int id);

    int removeMetaInfo(int id );

    int addMetaInfo(MetaInfo metaInfo);

    boolean updateStartSwitch(@Param("id")int id, @Param("startSwitch")int startSwitch);

    boolean changeUpdateTime(@Param("id")int id, @Param("updateTime")long updateTime);
}
