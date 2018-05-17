package com.bigsea.study.studythread.volatitle;

public class PrintString implements Runnable {

    private volatile boolean isContinuePrint = true;

    public void print(){
        while (isContinuePrint){
            try {
                System.out.println("run printMethod  currentThread"+Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        print();
    }

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }


}
