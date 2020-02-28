package com.example.demo.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "ShoppingCartBean")
public class ShoppingCartBean {
    @Column(name = "id", isId = true,autoGen = false)
    private int id;

    @Column(name = "imageUrl")
    private String imageUrl;//图片地址，默认取第一个地址

    @Column(name = "shoppingName")
    private String shoppingName;//商品名字或者描述

    @Column(name = "dressSize")
    private int dressSize;//尺码大小

    @Column(name = "price")
    private double price;//商品价格

    public boolean isChoosed;//是否选择状态

    public boolean isCheck = false;//是否已经校验

    @Column(name = "count")
    private int count;

    public ShoppingCartBean() {
    }

    public ShoppingCartBean(int id, String shoppingName, int dressSize, double price, int count) {
        this.id = id;
        this.shoppingName = shoppingName;
        this.dressSize = dressSize;
        this.price = price;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }


    public int getDressSize() {
        return dressSize;
    }

    public void setDressSize(int dressSize) {
        this.dressSize = dressSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingCartBean{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", shoppingName='" + shoppingName + '\'' +
                ", dressSize=" + dressSize +
                ", price=" + price +
                ", isChoosed=" + isChoosed +
                ", isCheck=" + isCheck +
                ", count=" + count +
                '}';
    }
}
