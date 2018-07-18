package com.lzz;

import com.lzz.app.dao.IMetaInfoDao;
import com.lzz.app.model.MetaInfo;
import com.lzz.app.util.DateUtil;
import com.lzz.component.source.hbase.UpdateHbaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import util.dconcurrent.DExecutors;
import util.dconcurrent.DFuture;
import util.dconcurrent.util.HostAndPort;
import java.util.List;

/**
 * Created by gl49 on 2018/6/23.
 */
@Component
public class ScanSchedule implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IMetaInfoDao metaInfoDao;
    @Value("${dconcurrent.hostList}")
    private String hostListStr;
    @Value("${dconcurrent.serverport}")
    private int serverPort;
    private DExecutors client;

    public ScanSchedule(){

    }

    public ScanSchedule(IMetaInfoDao metaInfoDao){
        this.metaInfoDao = metaInfoDao;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DExecutors.serverStart( serverPort );
        List<HostAndPort> hostAndPortList = DExecutors.getHostListByStrAddress( hostListStr );
        client = DExecutors.newFixDExecutor( hostAndPortList );
    }

    // 必须加入 initialDelay， 不然会比 onApplication 还要先初始化
    @Scheduled(initialDelay=1000, fixedRate = 1000 * 10)
    public void scheduleScanDataSource(){
        try {
            if( null == client || !client.isLeader() ){
                return;
            }
            List<MetaInfo> metaInfos = metaInfoDao.getMetaInfoStartSwitchList();
            for(MetaInfo metaInfo : metaInfos){
                long updateTime = metaInfo.getUpdateTime();
                int updateRange = metaInfo.getUpdateRangeTime();
                long current = DateUtil.current();
                if( current - updateTime > updateRange ){
                    DFuture<Boolean> future = client.submit(new UpdateHbaseTask(metaInfo), String.valueOf(metaInfo.getId()));
                    Boolean finishUpdate = future.get();
                    if( finishUpdate ){ // 成功要修改一下 update 时间保证，下次不会被继续调用
                        metaInfoDao.changeUpdateTime(metaInfo.getId(), DateUtil.current());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
