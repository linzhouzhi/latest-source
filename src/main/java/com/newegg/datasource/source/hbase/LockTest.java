package com.newegg.datasource.source.hbase;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gl49 on 2018/6/26.
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
        blockingQueue.put("fcdsa");


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        countDownLatch.countDown();
        countDownLatch.await();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        //cyclicBarrier.await()

        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();
        semaphore.release();

        ThreadLocal<Object> threadLocal = new ThreadLocal();
        threadLocal.get();
        threadLocal.set("fdsa");

        List list = new ArrayList<>();
        Iterator it = list.iterator();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition.await();
                    System.out.println("ffffff wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println("hhhhh wait");            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(4000);
                    condition.signal();
                    System.out.println("sing aaa");
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println("hhhhh sing");
            }
        });
        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}
