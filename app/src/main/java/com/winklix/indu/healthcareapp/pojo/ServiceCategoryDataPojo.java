package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class ServiceCategoryDataPojo {

    private String service_cat_id;
    private String service_cat_name;
    private String created_on;
    private String status;
    private String image;
    private ArrayList<ServiceSubCategoryPojo> subcategory;

    public String getService_cat_id() {
        return service_cat_id;
    }

    public void setService_cat_id(String service_cat_id) {
        this.service_cat_id = service_cat_id;
    }

    public String getService_cat_name() {
        return service_cat_name;
    }

    public void setService_cat_name(String service_cat_name) {
        this.service_cat_name = service_cat_name;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ServiceSubCategoryPojo> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ArrayList<ServiceSubCategoryPojo> subcategory) {
        this.subcategory = subcategory;
    }



}
