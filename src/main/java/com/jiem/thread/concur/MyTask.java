package com.jiem.thread.concur;

/**
 *
 * Created by jiem on 2018/4/27 23:26.
 */
public class MyTask implements Runnable{

    private int taskId;
    private String taskName;

    public void run() {
        try {
            System.out.println("run taskId =" + this.taskId);
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MyTask(  int taskId,String taskName) {
        this.taskName = taskName;
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
