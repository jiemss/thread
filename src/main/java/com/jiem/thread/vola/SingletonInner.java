package com.jiem.thread.vola;

/**
 * 单例 内部类
 * Created by jiem on 2018/4/23 23:37.
 */
public class SingletonInner {

    private static class Singletion {
        private static Singletion single = new Singletion();
    }

    public static Singletion getInstance(){
        return Singletion.single;
    }
}
