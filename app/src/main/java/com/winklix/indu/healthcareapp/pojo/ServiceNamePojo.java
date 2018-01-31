package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 23-01-2018.
 */

public class ServiceNamePojo {

    private String response;
    private String msg;
    private ArrayList<ServiceNameDataPojo> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<ServiceNameDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceNameDataPojo> data) {
        this.data = data;
    }


}
