package com.lzz.datasource.source.hbase;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gl49 on 2018/6/27.
 */
public class Test {
    public static void main(String[] args){
        Test test = new Test();
        Object object = new Object();
        HashMap hm = new HashMap();


        synchronized (test){
            System.out.println("fdas");
        }

        String s = new String();
        System.out.println( s );
        ReentrantLock reentrantLock = new ReentrantLock(true);

        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reentrantLock.unlock();
                }
            }).start();
        }

        User test2 = new User();


    }
}
