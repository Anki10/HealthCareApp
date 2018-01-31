package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/17/2018.
 */

public class CategoryDataPojo {

    private String category_id;
    private String category_name;
    private String created_on;
    private String status;
    private String image;
    private ArrayList<SubcategoryPojo> subcategory;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public ArrayList<SubcategoryPojo> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ArrayList<SubcategoryPojo> subcategory) {
        this.subcategory = subcategory;
    }



}
