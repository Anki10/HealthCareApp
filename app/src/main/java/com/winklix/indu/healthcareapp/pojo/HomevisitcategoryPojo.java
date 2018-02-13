package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 08-02-2018.
 */

public class HomevisitcategoryPojo {

    private String message;
    private ArrayList<HomevisitcategoryDataPojo> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<HomevisitcategoryDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<HomevisitcategoryDataPojo> data) {
        this.data = data;
    }


}
