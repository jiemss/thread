package com.jiem.thread.design;

/**
 *
 * Created by jiem on 2018/4/27 22:43.
 */
public class FutureClient {

    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();

        new Thread(new Runnable() {
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
}
