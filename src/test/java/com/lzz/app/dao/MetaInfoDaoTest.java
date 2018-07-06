package com.lzz.app.dao;

import com.lzz.Application;
import com.lzz.app.model.MetaInfo;
import org.apache.hadoop.hbase.MetaTableAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by gl49 on 2018/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MetaInfoDaoTest {
    @Autowired
    private IMetaInfoDao metaInfoDao;

    @Test
    public void addTest(){
        MetaInfo metaInfo = new MetaInfo();
        int res = metaInfoDao.addMetaInfo( metaInfo );
        System.out.println( res + "----------------------------------");
    }

    @Test
    public void removeTest(){
        int res = metaInfoDao.removeMetaInfo(3);
        System.out.println( res );
    }

    @Test
    public void  getMetaInfoListTest(){
        List<MetaInfo> list = metaInfoDao.getMetaInfoList();
        System.out.println( list );
    }

    @Test
    public void getMetaInfoTest(){
        MetaInfo metaInfo = metaInfoDao.getMetaInfo(1);
        System.out.println( metaInfo );
    }

    @Test
    public void updateStartSwitchTest(){
        boolean res = metaInfoDao.updateStartSwitch(1, 1);
        System.out.println( res );
    }
}
