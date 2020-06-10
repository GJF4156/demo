package com.example.demo.beans;

import java.util.List;

/**
 * @ 作者：上善若水
 * @ 时间：2020-06-10 19:33
 * @ 修改：于2020年06月10日更改
 * @ 描述：
 * @ 版本:
 */
public class Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gname : 鼠标
         * gtype : 0.0
         * aipre : 0.0
         * gexplain : 可回收垃圾指适宜回收、可循环利用的生活废弃物
         * gcontain : 常见包括各类废金属、玻璃瓶、易拉罐、饮料瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品、电子产品等
         * gtip : 轻投轻放；清洁干燥，避免污染，费纸尽量平整；立体包装物请清空内容物，清洁后压扁投放；有尖锐边角的、应包裹后投放
         */

        private String gname;
        private double gtype;
        private double aipre;
        private String gexplain;
        private String gcontain;
        private String gtip;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public double getGtype() {
            return gtype;
        }

        public void setGtype(double gtype) {
            this.gtype = gtype;
        }

        public double getAipre() {
            return aipre;
        }

        public void setAipre(double aipre) {
            this.aipre = aipre;
        }

        public String getGexplain() {
            return gexplain;
        }

        public void setGexplain(String gexplain) {
            this.gexplain = gexplain;
        }

        public String getGcontain() {
            return gcontain;
        }

        public void setGcontain(String gcontain) {
            this.gcontain = gcontain;
        }

        public String getGtip() {
            return gtip;
        }

        public void setGtip(String gtip) {
            this.gtip = gtip;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "gname='" + gname + '\'' +
                    ", gtype=" + gtype +
                    ", aipre=" + aipre +
                    ", gexplain='" + gexplain + '\'' +
                    ", gcontain='" + gcontain + '\'' +
                    ", gtip='" + gtip + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                '}';
    }
}
