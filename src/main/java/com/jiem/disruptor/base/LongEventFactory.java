package com.jiem.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * 需要让disruptor为我们创建事件，
 * 我们同时还声明了一个EventFactory来实例化Event对象。
 * <p/>
 * Created by jiem on 2018/5/6 20:57.
 */
public class LongEventFactory implements EventFactory {

    public Object newInstance() {
        return new LongEvent();
    }
}
