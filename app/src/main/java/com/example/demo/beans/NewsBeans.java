package com.example.demo.beans;

import java.util.List;

public class NewsBeans {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2020-02-05 13:00","title":"我国新指定7处国际重要湿地","description":"人民网环保","picUrl":"/NMediaFile/2020/0205/MAIN202002050840000195907127441.jpg","url":"http://env.people.com.cn/n1/2020/0205/c1010-31572401.html"},{"ctime":"2020-02-05 10:00","title":"密云水库巡河路程增加一倍","description":"人民网环保","picUrl":"/NMediaFile/2020/0205/MAIN202002050840000195907127441.jpg","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571733.html"},{"ctime":"2020-02-05 09:00","title":"利用预警系统加强疫情防控信息发布","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571251.html"},{"ctime":"2020-02-05 09:00","title":"严检测 保供水","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571253.html"},{"ctime":"2020-02-05 09:00","title":"三峡水库持续加大补水力度","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571254.html"},{"ctime":"2020-02-05 09:00","title":"黑龙江国控断面全部退出劣Ⅴ类","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571255.html"},{"ctime":"2020-02-05 09:00","title":"福建整合公益诉讼和生态检察职能","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571256.html"},{"ctime":"2020-02-05 09:00","title":"火神山医院废水垃圾单独处理","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571272.html"},{"ctime":"2020-02-05 09:00","title":"武汉医疗废物处置能力提升一倍多","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571291.html"},{"ctime":"2020-02-05 09:00","title":"捕捞、收购和贩卖环节都要承担侵权责任","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0205/c1010-31571316.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2020-02-05 13:00
         * title : 我国新指定7处国际重要湿地
         * description : 人民网环保
         * picUrl : /NMediaFile/2020/0205/MAIN202002050840000195907127441.jpg
         * url : http://env.people.com.cn/n1/2020/0205/c1010-31572401.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

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
    }
}
