package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class GetCartPojo {

    private String message;
    private ArrayList<GetCartDataPojo> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<GetCartDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<GetCartDataPojo> data) {
        this.data = data;
    }



}
