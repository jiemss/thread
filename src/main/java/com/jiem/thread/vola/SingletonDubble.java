package com.jiem.thread.vola;

/**
 * 单例 双检验
 * Created by jiem on 2018/4/23 23:39.
 */
public class SingletonDubble {

    private static SingletonDubble sd;

    public static SingletonDubble getSd(){
        if(sd == null){
            try {
                Thread.sleep(3000);  //模拟初始化对象的准备时间...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonDubble.class) {
                if(sd == null){
                    sd = new SingletonDubble();
                }
            }
        }
        return sd;
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonDubble.getSd().hashCode());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonDubble.getSd().hashCode());
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonDubble.getSd().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
