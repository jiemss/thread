package com.jiem.thread.sync;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 * <p/>
 * Created by jiem on 2018/4/22 22:50.
 */
public class LockString {

    public void method() {
        //new String("字符串常量")
        synchronized ("字符串常量") {
            while (true) {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
            }
        }
    }

    public static void main(String[] args) {

        final LockString ls = new LockString();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                ls.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                ls.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
