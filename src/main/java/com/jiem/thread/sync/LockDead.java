package com.jiem.thread.sync;

/**
 * 死锁问题，在设计程序时就应该避免双方相互持有对方的锁的情况
 * <p/>
 * Created by jiem on 2018/4/22 21:30.
 */
public class LockDead implements Runnable {

    private String tag;
    private static Object lock1 = new Object(); //锁1
    private static Object lock2 = new Object(); //锁2

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void run() {
        if ("a".equals(tag)) {
            synchronized (lock1) {
                try {
                    System.out.println("当前线程 : " + Thread.currentThread().getName() + " 进入lock1执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("当前线程 : " + Thread.currentThread().getName() + " 进入lock2执行");
                }
            }
        }
        if("b".equals(tag)){
            synchronized (lock2) {
                try {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
                }
            }
        }


    }

    public static void main(String[] args) {

        LockDead d1 = new LockDead();
        d1.setTag("a");
        LockDead d2 = new LockDead();
        d2.setTag("b");

        Thread t1 = new Thread(d1,"t1");
        Thread t2 = new Thread(d2,"t2");

        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }
}
