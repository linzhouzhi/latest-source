package com.lzz.component.source.hbase;

import com.lzz.app.model.MetaInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Pair;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gl49 on 2018/7/18.
 */
public class HbaseManager {
    public static Connection getHbaseConnection(MetaInfo metaInfo) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum",  metaInfo.getZkAddress() );
        configuration.set("hbase.client.retries.number", "3");
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    public static List<Pair<byte[],byte[]>> getPairList(Connection connection, String tableName) throws IOException {
        List<HRegionLocation> hRegionLocationList= connection.getRegionLocator(TableName.valueOf( tableName )).getAllRegionLocations();
        List<Pair<byte[],byte[]>> pairList = new LinkedList<>();
        for(HRegionLocation hRegionLocation:hRegionLocationList){
            HRegionInfo region = hRegionLocation.getRegionInfo();
            byte[] startKeys = region.getStartKey();
            byte[] endKeys = region.getEndKey();
            pairList.add(new Pair<>(startKeys,endKeys));
        }
        return pairList;
    }
}
