package com.example.demo.beans;

public class Reservation {
    private String id;
    private String gotime;
    private String person;
    private String telephone;
    private String address;
    private String sort;
    private String uid;
    private String status;
    private String ordertime;

    public Reservation() {
    }

    public Reservation(String id, String gotime, String person, String telephone, String address, String sort, String uid, String status, String ordertime) {
        this.id = id;
        this.gotime = gotime;
        this.person = person;
        this.telephone = telephone;
        this.address = address;
        this.sort = sort;
        this.uid = uid;
        this.status = status;
        this.ordertime = ordertime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGotime() {
        return gotime;
    }

    public void setGotime(String gotime) {
        this.gotime = gotime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", gotime='" + gotime + '\'' +
                ", person='" + person + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", sort='" + sort + '\'' +
                ", uid='" + uid + '\'' +
                ", status='" + status + '\'' +
                ", ordertime='" + ordertime + '\'' +
                '}';
    }
}
