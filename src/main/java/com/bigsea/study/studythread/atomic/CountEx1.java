package com.bigsea.study.studythread.atomic;

import com.bigsea.study.studythread.annoations.NotThreadSafe;
import com.bigsea.study.studythread.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 使用
 */
@ThreadSafe
public class CountEx1 {
    // 请求总数
    public static int clientTotal = 5;
    // 允许并发执行的线程数
    public static int threadTotal = 2;

    public static AtomicInteger count = new AtomicInteger(0);

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
                    add();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count.get());
    }

    private static void add(){
        count.incrementAndGet();
    }


}
