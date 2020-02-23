package com.example.demo.beans;

import java.util.List;

public class Product {


    /**
     * msg : 查询成功
     * code : 200
     * productInfoList : [{"product":{"pid":1,"description":"花花公子男士外套春季工装百搭男装上衣服秋冬韩版潮流牛仔夹克男 常规款/加绒款可选","price":485,"number":100,"sold":23},"imagesPath":[{"imgId":1,"pid":1,"imgPath":"https://img.alicdn.com/imgextra/i2/2047509713/O1CN01lmZeD12LcbZDald2n_!!2047509713.jpg_430x430q90.jpg"}]},{"product":{"pid":2,"description":"男装 洗旧无褶直筒长裤 418917 优衣库UNIQLO 初上市价格249元 初上市尺码范围:70-95","price":99,"number":80,"sold":16},"imagesPath":[{"imgId":2,"pid":2,"imgPath":"https://img.alicdn.com/imgextra/i3/196993935/O1CN01fk71Vw1ewH6XAfk7p_!!0-item_pic.jpg_430x430q90.jpg"}]},{"product":{"pid":3,"description":"恒源祥羊毛衫男鸡心领套头长袖T恤针织打底衫男士V领薄款毛衣国货","price":1298,"number":20,"sold":12},"imagesPath":[{"imgId":3,"pid":3,"imgPath":"https://img.alicdn.com/imgextra/https://img.alicdn.com/bao/uploaded/i2/692297611/O1CN01265svGIziQxKO6K_!!692297611.jpg_430x430q90.jpg"}]},{"product":{"pid":4,"description":"男装 军装风茄克 420667 优衣库UNIQLO","price":199,"number":70,"sold":33},"imagesPath":[{"imgId":4,"pid":4,"imgPath":"https://img.alicdn.com/imgextra/i4/196993935/O1CN01cguMSX1ewH6SWoeUm_!!0-item_pic.jpg_430x430q90.jpg"}]}]
     */

    private String msg;
    private int code;
    private List<ProductInfoListBean> productInfoList;

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

    public List<ProductInfoListBean> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfoListBean> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public static class ProductInfoListBean {
        /**
         * product : {"pid":1,"description":"花花公子男士外套春季工装百搭男装上衣服秋冬韩版潮流牛仔夹克男 常规款/加绒款可选","price":485,"number":100,"sold":23}
         * imagesPath : [{"imgId":1,"pid":1,"imgPath":"https://img.alicdn.com/imgextra/i2/2047509713/O1CN01lmZeD12LcbZDald2n_!!2047509713.jpg_430x430q90.jpg"}]
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
             * pid : 1
             * description : 花花公子男士外套春季工装百搭男装上衣服秋冬韩版潮流牛仔夹克男 常规款/加绒款可选
             * price : 485
             * number : 100
             * sold : 23
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
             * imgId : 1
             * pid : 1
             * imgPath : https://img.alicdn.com/imgextra/i2/2047509713/O1CN01lmZeD12LcbZDald2n_!!2047509713.jpg_430x430q90.jpg
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
