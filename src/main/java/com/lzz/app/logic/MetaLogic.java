package com.lzz.app.logic;

import com.lzz.app.dao.CuratorZookeeperClient;
import com.lzz.app.dao.IMetaInfoDao;
import com.lzz.app.dao.ZookeeperClient;
import com.lzz.app.model.MetaInfo;
import com.lzz.app.util.NetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gl49 on 2018/7/6.
 */
@Component
public class MetaLogic {
    public static final String TOPIC_PATH =  "/brokers/topics";
    public static final String BROKERS_PATH =  "/brokers/ids";

    @Value("${dconcurrent.hostList}")
    private String hostListStr;

    @Autowired
    private IMetaInfoDao metaInfoDao;

    public String getTaskNodes(){
        return hostListStr;
    }

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

    public static boolean checkZookeeperAddress(String zk){
        boolean res =NetUtil.checkHost(zk);
        return  res;
    }
    public static boolean checkKafkaTopic(String zk, String topic) {
        boolean checkRes = false;
        try {
            List<String> topicList = getTopicList(zk);
            System.out.println( topicList );
            for(int i = 0; i < topicList.size(); i++ ){
                if( topic.equals(topicList.get(i)) ){
                    checkRes = true;
                    break;
                }
            }
        }catch (Exception e){

        }
        return checkRes;
    }

    public static List<String> getTopicList(String zk) throws Exception {
        List<String> topicList = new ArrayList<>();
        ZookeeperClient curatorZookeeperClient = null;
        try {
            curatorZookeeperClient = new CuratorZookeeperClient(zk, 10000);
            List<String> topics = curatorZookeeperClient.listChildrenNode( TOPIC_PATH );
            for(String topic : topics){
                topicList.add(topic);
            }
        }finally {
            curatorZookeeperClient.shutDown();
        }
        return topicList;
    }

    public static String getKafkaBrokerList(String zk){
        String brokerList = "";
        ZookeeperClient curatorZookeeperClient = null;
        if( StringUtils.isBlank( zk ) ){
            return brokerList;
        }
        try {
            curatorZookeeperClient = new CuratorZookeeperClient(zk, 10000);
            try {
                List<String> brokers = curatorZookeeperClient.listChildrenNode( BROKERS_PATH );
                for(String brokerPath : brokers){
                    try {
                        byte[] resByte = curatorZookeeperClient.getData( BROKERS_PATH + "/" + brokerPath );
                        String resStr = new String(resByte, StandardCharsets.UTF_8);
                        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(resStr);
                        String host = jsonObject.getString("host");
                        String port = jsonObject.getString("port");
                        brokerList += host + ":" + port;
                    }catch (Exception ignore){

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }finally {
            curatorZookeeperClient.shutDown();
        }
        return brokerList;
    }
}
