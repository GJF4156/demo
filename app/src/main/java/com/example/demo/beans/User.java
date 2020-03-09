package com.example.demo.beans;

public class User {
    private int user_id;//用户id
    private String user_nickName;//用户昵称
    private String user_name;//用户姓名
    private String user_brith;//用户生日
    private String user_idcard;//身份证
    private String user_photo;//头像
    private String usertelephone;//电话号码
    private String user_location;//地址
    private String user_sex;//性别
    private String userpassword;//密码
    private String user_reDate;//注册时间
    private String user_last_loginDate;//最后一次登录时间
    private Integer Wl_id;//钱包id
    private String token;//token

    public User() {
    }

    public User(int user_id, String user_nickName, String user_name, String user_brith, String user_idcard, String user_photo, String usertelephone, String user_location, String user_sex, String userpassword, String user_reDate, String user_last_loginDate, Integer wl_id, String token) {
        this.user_id = user_id;
        this.user_nickName = user_nickName;
        this.user_name = user_name;
        this.user_brith = user_brith;
        this.user_idcard = user_idcard;
        this.user_photo = user_photo;
        this.usertelephone = usertelephone;
        this.user_location = user_location;
        this.user_sex = user_sex;
        this.userpassword = userpassword;
        this.user_reDate = user_reDate;
        this.user_last_loginDate = user_last_loginDate;
        this.Wl_id = wl_id;
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickName() {
        return user_nickName;
    }

    public void setUser_nickName(String user_nickName) {
        this.user_nickName = user_nickName;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_brith() {
        return user_brith;
    }

    public void setUser_brith(String user_brith) {
        this.user_brith = user_brith;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUsertelephone() {
        return usertelephone;
    }

    public void setUsertelephone(String usertelephone) {
        this.usertelephone = usertelephone;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUser_reDate() {
        return user_reDate;
    }

    public void setUser_reDate(String user_reDate) {
        this.user_reDate = user_reDate;
    }

    public String getUser_last_loginDate() {
        return user_last_loginDate;
    }

    public void setUser_last_loginDate(String user_last_loginDate) {
        this.user_last_loginDate = user_last_loginDate;
    }

    public Integer getWl_id() {
        return Wl_id;
    }

    public void setWl_id(Integer wl_id) {
        Wl_id = wl_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_nickName='" + user_nickName + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_brith='" + user_brith + '\'' +
                ", user_idcard='" + user_idcard + '\'' +
                ", user_photo='" + user_photo + '\'' +
                ", usertelephone='" + usertelephone + '\'' +
                ", user_location='" + user_location + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", user_reDate='" + user_reDate + '\'' +
                ", user_last_loginDate='" + user_last_loginDate + '\'' +
                ", Wl_id=" + Wl_id +
                ", token='" + token + '\'' +
                '}';
    }
}
