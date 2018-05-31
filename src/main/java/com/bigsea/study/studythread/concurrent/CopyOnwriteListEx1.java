package com.bigsea.study.studythread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CopyOnwriteListEx1 {

    // 请求总数
    public static int clientTotal = 5000;
    // 允许并发执行的线程数
    public static int threadTotal = 50;

    public static List<Integer> list = new CopyOnWriteArrayList<Integer>();

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
