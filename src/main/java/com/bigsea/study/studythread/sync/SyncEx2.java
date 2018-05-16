package com.bigsea.study.studythread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncEx2 {


    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        SyncEx2 syn1 = new SyncEx2();
        SyncEx2 syn2 = new SyncEx2();
        executor.execute(()->{
            syn1.test2();
        });
        executor.execute(()->{
            syn2.test2();
        });
        executor.shutdown();
    }
    //修饰静态方法
    public static synchronized void test1(){
        for (int i=0;i<50;i++){
            System.out.println(i);
        }
    }
    //静态方法修饰类
    public static void test2(){
        synchronized (SyncEx2.class){
            for (int i=0;i<50;i++){
                System.out.println(i);
            }
        }
    }


}
