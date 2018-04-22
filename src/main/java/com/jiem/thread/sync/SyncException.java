package com.jiem.thread.sync;

/**
 * synchronized异常
 * <p/>
 * Created by jiem on 2018/4/22 21:05.
 */
public class SyncException {

    private int i = 0;

    public synchronized void operation(){
        while (true){
            i++;
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+ " , i = " + i);
                if (i==20){
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        final SyncException se = new SyncException();

        new Thread(new Runnable() {
            public void run() {
                se.operation();
            }
        },"t1").start();

    }
}
