package com.bigsea.study.studythread;

import com.bigsea.study.studythread.annoations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@NotThreadSafe
public class ConcurrencyTest {
    // 请求总数
    public static int clientTotal = 5000;
    // 允许并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

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
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          });
          countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);
    }

    private static void add(){
        count++;
    }

}
