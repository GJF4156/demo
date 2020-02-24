package com.example.demo.beans;

import java.util.List;

public class ProductInfo {

    /**
     * msg : 查询成功
     * code : 200
     * productInfo : {"product":{"pid":6,"description":"2020春季欧美新款时尚双排扣外套女装中长款长袖韩版休闲针织风衣","price":249,"number":5,"sold":2},"imagesPath":[{"imgId":8,"pid":6,"imgPath":"https://gd4.alicdn.com/imgextra/i4/735214850/O1CN01ttMJPy1lhLVIAUqmX_!!735214850.jpg_400x400.jpg"},{"imgId":9,"pid":6,"imgPath":"https://gd3.alicdn.com/imgextra/i3/735214850/O1CN011A0dP31lhLVLgakeT_!!735214850.jpg_50x50.jpg_.webp"},{"imgId":10,"pid":6,"imgPath":"https://gd4.alicdn.com/imgextra/i4/735214850/O1CN01KMpcnV1lhLVJzgp9Y_!!735214850.jpg_50x50.jpg_.webp"}]}
     */

    private String msg;
    private int code;
    private ProductInfoBean productInfo;

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

    public ProductInfoBean getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfoBean productInfo) {
        this.productInfo = productInfo;
    }

    public static class ProductInfoBean {
        /**
         * product : {"pid":6,"description":"2020春季欧美新款时尚双排扣外套女装中长款长袖韩版休闲针织风衣","price":249,"number":5,"sold":2}
         * imagesPath : [{"imgId":8,"pid":6,"imgPath":"https://gd4.alicdn.com/imgextra/i4/735214850/O1CN01ttMJPy1lhLVIAUqmX_!!735214850.jpg_400x400.jpg"},{"imgId":9,"pid":6,"imgPath":"https://gd3.alicdn.com/imgextra/i3/735214850/O1CN011A0dP31lhLVLgakeT_!!735214850.jpg_50x50.jpg_.webp"},{"imgId":10,"pid":6,"imgPath":"https://gd4.alicdn.com/imgextra/i4/735214850/O1CN01KMpcnV1lhLVJzgp9Y_!!735214850.jpg_50x50.jpg_.webp"}]
         */

        private ProductBean product;
        private List<ImagesPathBean> imagesPath;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public List<ImagesPathBean> getImagesPath() {
            return imagesPath;
        }

        public void setImagesPath(List<ImagesPathBean> imagesPath) {
            this.imagesPath = imagesPath;
        }

        public static class ProductBean {
            /**
             * pid : 6
             * description : 2020春季欧美新款时尚双排扣外套女装中长款长袖韩版休闲针织风衣
             * price : 249
             * number : 5
             * sold : 2
             */

            private int pid;
            private String description;
            private int price;
            private int number;
            private int sold;

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getSold() {
                return sold;
            }

            public void setSold(int sold) {
                this.sold = sold;
            }
        }

        public static class ImagesPathBean {
            /**
             * imgId : 8
             * pid : 6
             * imgPath : https://gd4.alicdn.com/imgextra/i4/735214850/O1CN01ttMJPy1lhLVIAUqmX_!!735214850.jpg_400x400.jpg
             */

            private int imgId;
            private int pid;
            private String imgPath;

            public int getImgId() {
                return imgId;
            }

            public void setImgId(int imgId) {
                this.imgId = imgId;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }
        }
    }
}
