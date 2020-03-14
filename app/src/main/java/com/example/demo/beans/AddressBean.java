package com.example.demo.beans;

import java.util.List;

public class AddressBean {

    /**
     * code : 100
     * msg : 处理成功！
     * info : {"addresses":[{"aid":5,"receiver":"测试","phone":"1377777777","address":"北京市-东城区-测试","uid":"13684433731"},{"aid":6,"receiver":"测试","phone":"1377777777","address":"北京市-东城区-测试","uid":"13684433731"},{"aid":7,"receiver":"测试","phone":"13777777777","address":"测试","uid":"13684433731"},{"aid":8,"receiver":"测试二","phone":"13684433731","address":"四川省-遂宁市-大英县-明星西街35号","uid":"13684433731"},{"aid":9,"receiver":"郭建风","phone":"13797976868","address":"北京市-东城区-随便地址","uid":"13684433731"}]}
     */

    private int code;
    private String msg;
    private InfoBean info;

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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private List<AddressesBean> addresses;

        public List<AddressesBean> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<AddressesBean> addresses) {
            this.addresses = addresses;
        }

        public static class AddressesBean {
            /**
             * aid : 5
             * receiver : 测试
             * phone : 1377777777
             * address : 北京市-东城区-测试
             * uid : 13684433731
             */

            private int aid;
            private String receiver;
            private String phone;
            private String address;
            private String uid;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
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
        }
    }
}
