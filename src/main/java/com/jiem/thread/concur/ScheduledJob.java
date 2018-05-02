package com.jiem.thread.concur;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by jiem on 2018/4/27 23:12.
 */
class Temp extends Thread {
    @Override
    public void run() {
        System.out.println("run");
    }
}

public class ScheduledJob{

    public static void main(String[] args) {
        Temp temp = new Temp();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(temp, 5, 1, TimeUnit.SECONDS);
    }

}