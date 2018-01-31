package com.winklix.indu.healthcareapp.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 1/17/2018.
 */

public class SubcategoryPojo implements Serializable {

    private String subcategory_id;
    private String fk_category_id;
    private String subcategory_name;
    private String status;
    private String created_on;
    private String image;

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getFk_category_id() {
        return fk_category_id;
    }

    public void setFk_category_id(String fk_category_id) {
        this.fk_category_id = fk_category_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
