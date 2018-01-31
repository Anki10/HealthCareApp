package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/17/2018.
 */

public class CategoryPojo {

    private String message;
    private ArrayList<CategoryDataPojo> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CategoryDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryDataPojo> data) {
        this.data = data;
    }



}
