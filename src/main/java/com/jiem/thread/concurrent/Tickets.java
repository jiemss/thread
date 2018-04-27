package com.jiem.thread.concurrent;

import java.util.*;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 * Created by jiem on 2018/4/24 19:02.
 */
public class Tickets {

    public static void main(String[] args) {

        //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap

        //final List<String> tickets =new ArrayList<String>() ;
        //final List<String> tickets = Collections.synchronizedList(new ArrayList<String>());
        final Vector<String> tickets = new Vector<String>();

        for (int i = 0; i < 100; i++) {
            tickets.add("火车票" + i);
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }, "\"线程\"+i").start();
        }
    }

}
