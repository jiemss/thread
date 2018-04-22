package com.jiem.thread.sync;

/**
 * 同一对象属性的修改不会影响锁的情况
 * Created by jiem on 2018/4/22 22:14.
 */
public class LockModifyObject {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public synchronized void changeAttributte(String name, int age) {

        System.out.println("当前线程 : " + Thread.currentThread().getName() + " 开始");

        this.setName(name);
        this.setAge(age);

        System.out.println("当前线程 : " + Thread.currentThread().getName() + " 修改对象内容为： "
                + this.getName() + ", " + this.getAge());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程 : " + Thread.currentThread().getName() + " 结束");

    }

    public static void main(String[] args) {
        final LockModifyObject lmo = new LockModifyObject();

        new Thread(new Runnable() {
            public void run() {
                lmo.changeAttributte("jiem",20);
            }
        },"t1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                lmo.changeAttributte("hello",21);
            }
        },"t2").start();
    }

}
