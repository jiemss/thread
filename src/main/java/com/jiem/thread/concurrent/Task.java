package com.jiem.thread.concurrent;

/**
 * Created by jiem on 2018/4/27 21:16.
 */
public class Task implements Comparable<Task> {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int compareTo(Task task) {
        return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
    }
}
