package com.bigsea.study.studythread.volatitle;

import com.bigsea.study.studythread.pojo.Role;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest2 {

    public static void main(String[] args) {
        Role role = new Role();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(role.getAge());
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                role.setAge(10);
            }
        });

        executor.shutdown();

    }

}
