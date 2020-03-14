package com.example.demo.beans;

public class User {
    private int userid;//用户id
    private String usernickName;//用户昵称
    private String username;//用户姓名
    private String userbrith;//用户生日
    private String useridcard;//身份证
    private String userphoto;//头像
    private String usertelephone;//电话号码
    private String userlocation;//地址
    private String usersex;//性别
    private String userpassword;//密码
    private String userreDate;//注册时间
    private String userlastloginDate;//最后一次登录时间
    private String token;//token

    public User() {
    }

    public User(int userid, String usernickName, String username, String userbrith, String useridcard, String userphoto, String usertelephone, String userlocation, String usersex, String userpassword, String userreDate, String userlastloginDate,  String token) {
        this.userid = userid;
        this.usernickName = usernickName;
        this.username = username;
        this.userbrith = userbrith;
        this.useridcard = useridcard;
        this.userphoto = userphoto;
        this.usertelephone = usertelephone;
        this.userlocation = userlocation;
        this.usersex = usersex;
        this.userpassword = userpassword;
        this.userreDate = userreDate;
        this.userlastloginDate = userlastloginDate;
        this.token = token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsernickName() {
        return usernickName;
    }

    public void setUsernickName(String usernickName) {
        this.usernickName = usernickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserbrith() {
        return userbrith;
    }

    public void setUserbrith(String userbrith) {
        this.userbrith = userbrith;
    }

    public String getUseridcard() {
        return useridcard;
    }

    public void setUseridcard(String useridcard) {
        this.useridcard = useridcard;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getUsertelephone() {
        return usertelephone;
    }

    public void setUsertelephone(String usertelephone) {
        this.usertelephone = usertelephone;
    }

    public String getUserlocation() {
        return userlocation;
    }

    public void setUserlocation(String userlocation) {
        this.userlocation = userlocation;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserreDate() {
        return userreDate;
    }

    public void setUserreDate(String userreDate) {
        this.userreDate = userreDate;
    }

    public String getUserlastloginDate() {
        return userlastloginDate;
    }

    public void setUserlastloginDate(String userlastloginDate) {
        this.userlastloginDate = userlastloginDate;
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
                "userid=" + userid +
                ", usernickName='" + usernickName + '\'' +
                ", username='" + username + '\'' +
                ", userbrith='" + userbrith + '\'' +
                ", useridcard='" + useridcard + '\'' +
                ", userphoto='" + userphoto + '\'' +
                ", usertelephone='" + usertelephone + '\'' +
                ", userlocation='" + userlocation + '\'' +
                ", usersex='" + usersex + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", userreDate='" + userreDate + '\'' +
                ", userlastloginDate='" + userlastloginDate + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
