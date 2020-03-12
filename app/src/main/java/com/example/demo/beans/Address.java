package com.example.demo.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "Address")
public class Address {
    @Column(name = "aid", isId = true,autoGen =true)
    private int aid;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    private String uid;

    public Address() {
    }

    public Address(int aid, String receiver, String phone, String address, String uid) {
        this.aid = aid;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.uid = uid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", receiver='" + receiver + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}