package com.bigsea.study.studythread.volatitle;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintStringTest {
    static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

 /*       ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    flag=true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!flag){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("开始执行。。。。");
            }
        });

        executor.shutdown();*/

        PrintString printString = new PrintString();
        Thread thread = new Thread(printString);
        thread.start();
        Thread.sleep(10000);
        printString.setContinuePrint(false);

    }



}