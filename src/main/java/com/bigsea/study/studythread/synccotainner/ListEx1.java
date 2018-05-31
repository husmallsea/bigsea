package com.bigsea.study.studythread.synccotainner;

import com.bigsea.study.studythread.annoations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class ListEx1 {

    // 请求总数
    public static int clientTotal = 5000;
    // 允许并发执行的线程数
    public static int threadTotal = 50;

    public static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal;i++){
            final int index = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(index);
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(list.size());
    }

    private static void update(int index){

        list.add(index);
    }

}
