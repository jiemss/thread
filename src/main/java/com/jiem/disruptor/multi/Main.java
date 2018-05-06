package com.jiem.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 *
 * Created by jiem on 2018/5/6 18:29.
 */
public class Main {

    public static void main(String[] args) {

        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                new EventFactory<Order>() {
                    public Order newInstance() {
                        return new Order();
                    }
                },
                1024 * 1024,
                new YieldingWaitStrategy()
            );

        SequenceBarrier barrier = ringBuffer.newBarrier();

        Consumer[] consumers = new Consumer[3];

        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("c" + 1);
        }

        WorkerPool<Order> workerPool = new WorkerPool<Order>(
                ringBuffer,
                barrier,
                new IntEventExceptionHandler(),
                consumers
            );

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        p.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();

            try {

                Thread.sleep(2000);
                System.out.println("---------------开始生产-----------------");
                latch.countDown();
                Thread.sleep(5000);
                System.out.println("总数:" + consumers[0].getCount());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class IntEventExceptionHandler implements ExceptionHandler {
        public void handleEventException(Throwable ex, long sequence, Object event) {
        }

        public void handleOnStartException(Throwable ex) {
        }

        public void handleOnShutdownException(Throwable ex) {
        }
    }
}
