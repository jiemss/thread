package com.jiem.thread.sync;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * <p/>
 * Created by jiem on 2018/4/22 16:54.
 */
public class DirtyRead {

    private String username = "jiem";
    private String password = "1234";

    public synchronized void setValue(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    /*synchronized*/
    public synchronized void getValue() {
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }

    public static void main(String[] args) {

        final DirtyRead dr = new DirtyRead();

        new Thread(new Runnable() {
            public void run() {
                dr.setValue("hello", "world");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dr.getValue();
    }
}
