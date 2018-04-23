package com.jiem.thread.vola;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟Queue
 * Created by jiem on 2018/4/23 23:07.
 */
public class MyQueue {

    private final LinkedList<Object> list = new LinkedList<Object>();

    private final AtomicInteger count = new AtomicInteger(0);

    private final int maxSize;
    private final int minSize = 0;

    private final Object lock = new Object();

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Object object) {
        synchronized (lock) {
            while (count.get() == maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(object);
            count.getAndIncrement();
            System.out.println(" 元素 " + object + " 被添加 ");
            lock.notify();
        }
    }

    public Object take() {
        Object obj ;
        synchronized (lock) {
            while (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count.getAndDecrement();
            obj = list.removeFirst();
            System.out.println(" 元素 " + obj + " 被消费 ");
            lock.notify();
        }
        return obj;
    }

    public int size(){
        return count.get();
    }

    public static void main(String[] args) {
        final MyQueue m = new MyQueue(5);
        m.put("a");
        m.put("b");
        m.put("c");
        m.put("d");
        m.put("e");

        System.out.println("当前元素个数：" + m.size());

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                m.put("h");
                m.put("i");
            }
        },"thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    Object o1 = m.take();

                    Thread.sleep(1000);
                    Object o2 = m.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread2");

        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

}



