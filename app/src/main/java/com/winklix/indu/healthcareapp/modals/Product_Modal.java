package com.winklix.indu.healthcareapp.modals;

/**
 * Created by harsh on 27/11/2017.
 */

public class Product_Modal {


    private String prod_id;
    private String category_id;
    private String size_id;
    private String subcategory_id;
    private String discount_id;
    private String prod_name;
    private String prod_price;
    private String prod_description;
    private String prod_specification;
    private String prod_features;
    private String product_images;
    private String thumb;
    private String hsn_code;




    public Product_Modal(String prod_id, String prod_name, String prod_img, String prod_prize, String prod_desc) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.product_images = prod_img;
        this.prod_price = prod_prize;
        this.prod_description = prod_desc;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(String discount_id) {
        this.discount_id = discount_id;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_description() {
        return prod_description;
    }

    public void setProd_description(String prod_description) {
        this.prod_description = prod_description;
    }

    public String getProd_specification() {
        return prod_specification;
    }

    public void setProd_specification(String prod_specification) {
        this.prod_specification = prod_specification;
    }

    public String getProd_features() {
        return prod_features;
    }

    public void setProd_features(String prod_features) {
        this.prod_features = prod_features;
    }

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(String hsn_code) {
        this.hsn_code = hsn_code;
    }



    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_img() {
        return product_images;
    }

    public void setProd_img(String prod_img) {
        this.product_images = prod_img;
    }

    public String getProd_prize() {
        return prod_price;
    }

    public void setProd_prize(String prod_prize) {
        this.prod_price = prod_prize;
    }

    public String getProd_desc() {
        return prod_description;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_description = prod_desc;
    }



}