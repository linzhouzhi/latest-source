package com.lzz.app.util;

import java.io.IOException;
import java.net.*;

/**
 * Created by lzz on 2018/2/5.
 */
public class NetUtil {

    public static boolean checkHost(String address){
        String[] tmpArr = address.split(":");
        if( tmpArr.length == 2 ){
            return checkIpAndPort( tmpArr[0], Integer.valueOf(tmpArr[1]));
        }
        return true;
    }

    public static boolean checkIpAndPort(String ip, Integer port){
        boolean res = false;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip, port));
            res = true;
        } catch (IOException ignore) {

        } finally {
            try {
                socket.close();
            } catch (IOException ignore) {}
        }
        return res;
    }

}