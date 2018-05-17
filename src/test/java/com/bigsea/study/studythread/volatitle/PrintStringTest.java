package com.bigsea.study.studythread.volatitle;

import org.junit.Test;

public class PrintStringTest {

    @Test
    public void volatitleTest() throws InterruptedException {
        PrintString printString = new PrintString();
        Thread thread = new Thread(printString);
        thread.start();
        Thread.sleep(1000);
        printString.setContinuePrint(false);
    }


}