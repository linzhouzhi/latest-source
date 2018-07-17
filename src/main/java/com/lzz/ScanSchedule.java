package com.lzz;

import com.lzz.app.dao.IMetaInfoDao;
import com.lzz.app.model.MetaInfo;
import com.lzz.app.util.DateUtil;
import com.lzz.component.source.hbase.UpdateHbaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import util.dconcurrent.DExecutors;
import util.dconcurrent.DFuture;
import util.dconcurrent.util.HostAndPort;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gl49 on 2018/6/23.
 */
@Component
public class ScanSchedule {
    @Autowired
    private IMetaInfoDao metaInfoDao;

    public ScanSchedule(){

    }

    public ScanSchedule(IMetaInfoDao metaInfoDao){
        this.metaInfoDao = metaInfoDao;
    }

    private static DExecutors client;
    static {
        DExecutors.serverStart(50051);
        List<HostAndPort> hostAndPortList = new ArrayList<HostAndPort>();
        HostAndPort hostAndPort1 = new HostAndPort("10.16.164.33", 50051);
        HostAndPort hostAndPort2 = new HostAndPort("10.16.164.33", 50052);
        hostAndPortList.add( hostAndPort1 );
        hostAndPortList.add( hostAndPort2 );
        client = DExecutors.newFixDExecutor( hostAndPortList );
    }

    @Scheduled(fixedRate = 1000 * 10)
    public void scheduleScanDataSource(){
        try {
            if( !client.isLeader() ){
                return;
            }
            List<MetaInfo> metaInfos = metaInfoDao.getMetaInfoStartSwitchList();
            for(MetaInfo metaInfo : metaInfos){
                int updateTime = metaInfo.getUpdateTime();
                int updateRange = metaInfo.getUpdateRangeTime();
                int current = DateUtil.getTime();
                if( current - updateTime > updateRange ){
                    DFuture<Boolean> future = client.submit(new UpdateHbaseTask(metaInfo));
                    System.out.println( future.get() + "------------------ boolean");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
