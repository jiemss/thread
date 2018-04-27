package com.jiem.thread.design;

/**
 *
 * Created by jiem on 2018/4/27 22:40.
 */
public class FutureData implements Data {

    private RealData realData ;

    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        //如果已经装载完毕了，就直接返回
        if(isReady){
            return;
        }
        //如果没装载，进行装载真实对象
        this.realData = realData;
        isReady = true;
        //进行通知
        notify();
    }

    public synchronized String getRequest() {

        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }
}
