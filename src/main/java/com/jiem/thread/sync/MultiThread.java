package com.jiem.thread.sync;

/**
 * 关键字synchronize取得的锁都是对象锁，而且不是吧一段代码（方法）当做锁，
 * 所以代码中那种线程先执行synchronize发关键字的方法，那个线程就持有该方法所属对象的锁
 * <p/>
 * 静态方法上加synchronize关键字，表示锁定class类，类级别的锁。
 * <p/>
 * Created by jiem on 2018/4/22 15:31.
 */
public class MultiThread {

    public static synchronized void printNum(String tag) {

        int num;
        if ("a".equals(tag)) {
            num = 100;
            System.out.println("tag a, set num over!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("tag b, set num over!");
        }
        System.out.println("tag " + tag + ", num = " + num);
    }


    //注意观察run方法输出顺序
    public static void main(String[] args) {

        //俩个不同的对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();

    }


}
