package com.jiem.thread.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by jiem on 2018/4/27 21:14.
 */
public class UsePriorityBlockingQueue {
    public static void main(String[] args)  throws Exception{
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<Task>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");

        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");

        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        queue.add(t1);	//3
        queue.add(t2);	//4
        queue.add(t3);  //1

        System.out.println("容器：" + queue);
        System.out.println(queue.take().getId());
        System.out.println("容器：" + queue);
//		System.out.println(q.take().getId());
//		System.out.println(q.take().getId());

    }
}
