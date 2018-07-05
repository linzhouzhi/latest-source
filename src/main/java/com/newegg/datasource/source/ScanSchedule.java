package com.newegg.datasource.source;

import com.newegg.app.model.MetaInfo;
import com.newegg.datasource.source.hbase.HbaseScanTask;
import com.newegg.datasource.source.hbase.TmpTask;
import com.newegg.datasource.source.hbase.UpdateDataTask;
import io.grpc.distribute.DCallable;
import io.grpc.distribute.DConcurrentServer;
import io.grpc.distribute.DExecutors;
import io.grpc.distribute.util.HostAndPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Created by gl49 on 2018/6/23.
 */
public class ScanSchedule {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DConcurrentServer.daemonStart(50051);

        List<HostAndPort> hostAndPortList = new ArrayList<HostAndPort>();
        HostAndPort hostAndPort1 = new HostAndPort("", 50051);
        hostAndPortList.add( hostAndPort1 );
        DExecutors client = new DExecutors( hostAndPortList );
        while (true){
            if( !client.isLeader() ){
                System.out.println( "is not leader" );
                continue;
            }

            MetaInfo metaInfo = new MetaInfo(1, "", "", "", "", 10*60*1000, 1000 * 60 * 2);
            TmpTask tmpTask = new TmpTask( metaInfo );
            Future future = client.submit( tmpTask );
            System.out.println( future.get() );
            Thread.sleep(2000);
        }

    }

}
