package com.bigsea.study.studythread.atomic;

import com.bigsea.study.studythread.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicBoolean 使用
 */
@ThreadSafe
public class CountEx4 {
    // 请求总数
    public static int clientTotal = 2000;
    // 允许并发执行的线程数
    public static int threadTotal = 50;

    public static AtomicBoolean flag = new AtomicBoolean(false);

    public static Integer count = new Integer(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    // 只会有一个线程能够成功置flag为true
                   if( flag.compareAndSet(false,true)){
                       add();
                   };
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);
    }

    private static void add(){
        count++;
    }


}
