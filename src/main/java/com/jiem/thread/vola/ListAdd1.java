package com.jiem.thread.vola;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile的使用
 * Created by jiem on 2018/4/23 22:50.
 */
public class ListAdd1 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("jiem");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd1 listAdd1 = new ListAdd1();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        listAdd1.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + i + "添加了一个元素..");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (listAdd1.size() == 5) {
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();

    }
}
