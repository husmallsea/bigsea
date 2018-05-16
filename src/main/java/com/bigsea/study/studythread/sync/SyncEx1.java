package com.bigsea.study.studythread.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SyncEx1 {
    public static int count1 =0;
    public static int count2 =0;

    private static int clientNum = 30000;
    private static int threadNum = 2;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        Semaphore semaphore = new Semaphore(200);
        SyncEx1 syncEx1 = new SyncEx1();
        for (int i=0;i<2;i++){
            final int index = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    //syncEx1.count1(index);
                    syncEx1.count();
                    countDownLatch.countDown();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        countDownLatch.await();
        //System.out.println("count1 "+SyncEx1.count1+"------- count2 "+SyncEx1.count2 );
        executorService.shutdown();
        System.out.println(SyncEx1.count1);
    }



    public void count(){
        synchronized (this) {
            for (int i = 0; i < 800; i++) {
                System.out.println(i);
            }
        }
    }

    public synchronized void count1(int i){
        count1++;
    }


}
