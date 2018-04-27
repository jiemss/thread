package com.jiem.thread.concurrent;

import java.util.concurrent.DelayQueue;

/**
 * 队列 模拟网吧
 * Created by jiem on 2018/4/24 19:16.
 */
public class WangBa implements Runnable{

    private DelayQueue delayQueue = new DelayQueue();

    public boolean yinye = true;

    public void shangji(String name , String id ,int money){
        WangBaWangmin man = new WangBaWangmin(name,id,1000*money +System.currentTimeMillis());
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块,开始上机...");
        this.delayQueue.add(man);
    }

    public void xiaji(WangBaWangmin man){
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
    }


    public void run() {
        while(yinye){
            try {
                WangBaWangmin man = (WangBaWangmin) delayQueue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]){
        try{
            System.out.println("网吧开始营业");
            WangBa siyu = new WangBa();
            Thread shangwang = new Thread(siyu);
            shangwang.start();

            siyu.shangji("路人甲", "123", 1);
            siyu.shangji("路人乙", "234", 10);
            siyu.shangji("路人丙", "345", 5);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
