package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 29-01-2018.
 */

public class LocationServicePojo {

    private String response;
    private String msg;
    private ArrayList<LocationServiceDataPojo> data;

    public ArrayList<LocationServiceDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<LocationServiceDataPojo> data) {
        this.data = data;
    }

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

}
