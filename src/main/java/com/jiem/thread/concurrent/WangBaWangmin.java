package com.jiem.thread.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 上网用户的网名
 * Created by jiem on 2018/4/25 18:53.
 */
public class WangBaWangmin implements Delayed {

    private String name;
    //身份证
    private String id;
    //截止时间
    private long endTime;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public WangBaWangmin(String name, String id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * 用来判断是否到了截止时间
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return unit.convert(endTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }


    /**
     * 相互批较排序用
     * @param delayed
     * @return
     */
    public int compareTo(Delayed delayed) {
        WangBaWangmin w = (WangBaWangmin)delayed;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1:0;
    }
}
