package com.jiem.thread.vola;

/**
 * ThreadLocal的使用
 * Created by jiem on 2018/4/23 23:28.
 */
public class ConnThreadLocal {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void getThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + this.threadLocal.get());
    }

    public void setThreadLocal(String value) {
        threadLocal.set(value);
    }


    public static void main(String[] args) {

        final ConnThreadLocal ct = new ConnThreadLocal();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                ct.setThreadLocal("jiem");
                ct.getThreadLocal();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                ct.setThreadLocal("hello");
                ct.getThreadLocal();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
