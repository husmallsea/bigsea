package com.bigsea.study.studythread.atomic;

import com.bigsea.study.studythread.annoations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference
 */
@NotThreadSafe
public class CountEx5 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        System.out.println(count.get());
    }


}
