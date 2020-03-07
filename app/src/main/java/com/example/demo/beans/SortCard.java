package com.example.demo.beans;

public class SortCard {
    private String sortIcon;
    private String sortName;
    private String sortDescrip;
    private String content1;
    private String content2;
    private String content3;

    public SortCard() {
    }

    public SortCard(String sortIcon, String sortName, String sortDescrip, String content1, String content2, String content3) {
        this.sortIcon = sortIcon;
        this.sortName = sortName;
        this.sortDescrip = sortDescrip;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }

    public String getSortIcon() {
        return sortIcon;
    }

    public void setSortIcon(String sortIcon) {
        this.sortIcon = sortIcon;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDescrip() {
        return sortDescrip;
    }

    public void setSortDescrip(String sortDescrip) {
        this.sortDescrip = sortDescrip;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    @Override
    public String toString() {
        return "SortCard{" +
                "sortIcon='" + sortIcon + '\'' +
                ", sortName='" + sortName + '\'' +
                ", sortDescrip='" + sortDescrip + '\'' +
                ", content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                ", content3='" + content3 + '\'' +
                '}';
    }
}
