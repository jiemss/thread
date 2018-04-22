package com.jiem.thread.sync;

/**
 * synchronized的重入
 * <p/>
 * Created by jiem on 2018/4/22 17:12.
 */
public class SyncDubbo {
    public synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    public synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {

        final SyncDubbo sd = new SyncDubbo();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                sd.method1();
            }
        });

        t1.start();
    }
}

