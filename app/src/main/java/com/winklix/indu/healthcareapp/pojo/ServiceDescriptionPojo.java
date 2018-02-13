package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 23-01-2018.
 */

public class ServiceDescriptionPojo {

    private ArrayList<ServiceDescriptionDataPojo> data;
    private String msg;
    private String response;


    public ArrayList<ServiceDescriptionDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceDescriptionDataPojo> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
