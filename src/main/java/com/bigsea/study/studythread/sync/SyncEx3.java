package com.bigsea.study.studythread.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SyncEx3 {

    private static int count = 0;
    private static int clientTotal = 5000;
    private static int maxThreadNum = 200;
    private static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(maxThreadNum);
        for (int i =0;i<clientTotal;i++){
            final int index = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    countDownLatch.countDown();
                    semaphore.release();
                    //System.out.println(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
        System.out.println(count);
    }


    private static void add(){
        synchronized (SyncEx3.class){
            count++;
        }
    }




}
