package com.example.demo.beans;

public class OrdersBean {
    /**
     * msg : 查询成功
     * Order : {"odId":1,"odStatus":"已完成","odPayTime":"2019-11-20 01:22:25","odProductId":22,"odUserId":2,"odAddress":"四川省绵阳市涪城区西南财经大学天府学院","odTele":"19113716471","odPrice":98,"odExpressType":"韵达","odNumber":"2019.12.12","odShipTime":"2019-12-12 15:35:00","odClosingTime":"2019-12-20 18:19:00","odCreationTime":"2019-12-12 15:20:00"}
     * code : 200
     */
    private String msg;
    private OrderBean Order;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OrderBean getOrder() {
        return Order;
    }

    public void setOrder(OrderBean Order) {
        this.Order = Order;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class OrderBean {
        /**
         * odId : 1
         * odStatus : 已完成
         * odPayTime : 2019-11-20 01:22:25
         * odProductId : 22
         * odUserId : 2
         * odAddress : 四川省绵阳市涪城区西南财经大学天府学院
         * odTele : 19113716471
         * odPrice : 98
         * odExpressType : 韵达
         * odNumber : 2019.12.12
         * odShipTime : 2019-12-12 15:35:00
         * odClosingTime : 2019-12-20 18:19:00
         * odCreationTime : 2019-12-12 15:20:00
         */

        private int odId;
        private String odStatus;
        private String odPayTime;
        private int odProductId;
        private int odUserId;
        private String odAddress;
        private String odTele;
        private Double odPrice;
        private String odExpressType;
        private String odNumber;
        private String odShipTime;
        private String odClosingTime;
        private String odCreationTime;

        public int getOdId() {
            return odId;
        }

        public void setOdId(int odId) {
            this.odId = odId;
        }

        public String getOdStatus() {
            return odStatus;
        }

        public void setOdStatus(String odStatus) {
            this.odStatus = odStatus;
        }

        public String getOdPayTime() {
            return odPayTime;
        }

        public void setOdPayTime(String odPayTime) {
            this.odPayTime = odPayTime;
        }

        public int getOdProductId() {
            return odProductId;
        }

        public void setOdProductId(int odProductId) {
            this.odProductId = odProductId;
        }

        public int getOdUserId() {
            return odUserId;
        }

        public void setOdUserId(int odUserId) {
            this.odUserId = odUserId;
        }

        public String getOdAddress() {
            return odAddress;
        }

        public void setOdAddress(String odAddress) {
            this.odAddress = odAddress;
        }

        public String getOdTele() {
            return odTele;
        }

        public void setOdTele(String odTele) {
            this.odTele = odTele;
        }

        public Double getOdPrice() {
            return odPrice;
        }

        public void setOdPrice(Double odPrice) {
            this.odPrice = odPrice;
        }

        public String getOdExpressType() {
            return odExpressType;
        }

        public void setOdExpressType(String odExpressType) {
            this.odExpressType = odExpressType;
        }

        public String getOdNumber() {
            return odNumber;
        }

        public void setOdNumber(String odNumber) {
            this.odNumber = odNumber;
        }

        public String getOdShipTime() {
            return odShipTime;
        }

        public void setOdShipTime(String odShipTime) {
            this.odShipTime = odShipTime;
        }

        public String getOdClosingTime() {
            return odClosingTime;
        }

        public void setOdClosingTime(String odClosingTime) {
            this.odClosingTime = odClosingTime;
        }

        public String getOdCreationTime() {
            return odCreationTime;
        }

        public void setOdCreationTime(String odCreationTime) {
            this.odCreationTime = odCreationTime;
        }

        @Override
        public String toString() {
            return "OrderBean{" +
                    "odId=" + odId +
                    ", odStatus='" + odStatus + '\'' +
                    ", odPayTime='" + odPayTime + '\'' +
                    ", odProductId=" + odProductId +
                    ", odUserId=" + odUserId +
                    ", odAddress='" + odAddress + '\'' +
                    ", odTele='" + odTele + '\'' +
                    ", odPrice=" + odPrice +
                    ", odExpressType='" + odExpressType + '\'' +
                    ", odNumber='" + odNumber + '\'' +
                    ", odShipTime='" + odShipTime + '\'' +
                    ", odClosingTime='" + odClosingTime + '\'' +
                    ", odCreationTime='" + odCreationTime + '\'' +
                    '}';
        }
    }
}
