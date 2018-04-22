package com.jiem.thread.sync;

/**
 * 锁对象的改变问题
 * <p/>
 * Created by jiem on 2018/4/22 21:13.
 */
public class LockChangeObject {

    private String lock = "lock";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final LockChangeObject lc = new LockChangeObject();

        new Thread(new Runnable() {
            public void run() {
                lc.method();
            }
        }, "t1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                lc.method();
            }
        }, "t2").start();


    }

}
