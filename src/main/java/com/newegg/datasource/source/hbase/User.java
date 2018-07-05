package com.newegg.datasource.source.hbase;

/**
 * Created by gl49 on 2018/6/27.
 */
public class User {
    private static volatile  int num = 0;
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        synchronized (user){
            System.out.println("number : " + num);
        }
    }
}
