package com.jiem.thread.design;

import org.junit.Test;

/**
 *
 * Created by jiem on 2018/4/27 22:55.
 */
public class FutureClientTest {

    @Test
    public void test(){

        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功!");
        System.out.println("做其他的事情...");

        String result = data.getRequest();
        System.out.println(result);

    }

}