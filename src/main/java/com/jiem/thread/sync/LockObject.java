package com.jiem.thread.sync;

/**
 * 使用synchronized代码块加锁,比较灵活
 * <p/>
 * Created by jiem on 2018/4/22 22:30.
 */
public class LockObject {

    public void method1() {//对象锁
        synchronized (this) {
            try {
                System.out.println("do method1 ..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {  //类锁
        synchronized (LockObject.class) {
            try {
                System.out.println("do method2 ..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();

    public void method3() {        //任何对象锁
        synchronized (lock) {
            try {
                System.out.println("do method3..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final LockObject lockObject = new LockObject();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                lockObject.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                lockObject.method2();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                lockObject.method3();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

}
