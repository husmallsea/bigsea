package com.bigsea.study.studythread.commonUnsafe;

import com.bigsea.study.studythread.annoations.NotThreadSafe;
import com.bigsea.study.studythread.annoations.ThreadSafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class DateFormatEx1 {

    // 请求总数
    public static int clientTotal = 5000;
    // 允许并发执行的线程数
    public static int threadTotal = 50;

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD");

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
                    update();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update(){
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD");
        try {
            simpleDateFormat.parse("20180204");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
