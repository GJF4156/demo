package com.example.demo.beans;

import java.util.Map;

public class JsonBean {
    private String msg;
    private int code;
    private Map<String, Object> info;

    @Override
    public String toString() {
        return "JsonBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", info=" + info +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}

