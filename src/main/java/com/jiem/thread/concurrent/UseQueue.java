package com.jiem.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 队列
 * Created by jiem on 2018/4/27 19:10.
 */
public class UseQueue {
    public static void main(String[] args) throws Exception {

        //高性能无阻塞无界队列：ConcurrentLinkedQueue
        ConcurrentLinkedDeque<String> clq = new ConcurrentLinkedDeque<String>();
        clq.offer("h");
        clq.offer("e");
        clq.offer("l");
        clq.offer("l");
        clq.offer("o");

        System.out.println(clq.poll());
        System.out.println(clq.size());
        System.out.println(clq.peek());
        System.out.println(clq.size());


        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
        array.put("a");
        array.put("b");
        array.add("c");
        array.add("d");
        array.add("e");
        array.add("f");
        System.out.println(array.offer("a", 3, TimeUnit.SECONDS));



        //阻塞队列
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.offer("e");
        q.add("f");
        System.out.println(q.size());

        //for (Iterator iterator = q.iterator(); iterator.hasNext();) {
        //  String string = (String) iterator.next();
        //	System.out.println(string);
        //}

        List<String> list = new ArrayList<String>();
        System.out.println(q.drainTo(list, 3));
        System.out.println(list.size());
        for (String string : list) {
            System.out.println(string);
        }

        final SynchronousQueue<String> syncq = new SynchronousQueue<String>();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(syncq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        syncq.add("jiem");
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2");


        t1.start();
        t2.start();
    }


}
