package com.example.demo.beans;

public class OrderSimple {
    private int pid;
    private int num;

    public OrderSimple() {
    }

    public OrderSimple(int pid, int num) {
        this.pid = pid;
        this.num = num;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "OrderSimple{" +
                "pid=" + pid +
                ", num=" + num +
                '}';
    }
}
