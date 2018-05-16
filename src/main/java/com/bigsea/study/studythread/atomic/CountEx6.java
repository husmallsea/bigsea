package com.bigsea.study.studythread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicxxxFiledUpdater
 */
public class CountEx6 {

    public volatile int count=10;
    private static AtomicIntegerFieldUpdater<CountEx6> updater = AtomicIntegerFieldUpdater.newUpdater(CountEx6.class,"count");
    private static CountEx6 countEx6 = new CountEx6();
    public static void main(String[] args) {
        if(updater.compareAndSet(countEx6,10,15)){
            System.out.println(countEx6.getCount());
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
