package com.example.demo.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "NewsData")
public class NewsData {
    @Column(name = "id", isId = true,autoGen = true)
    private int id;
    @Column(name = "ctime")
    private String ctime;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "picUrl")
    private String picUrl;
    @Column(name = "url")
    private String url;

    public NewsData() {
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
