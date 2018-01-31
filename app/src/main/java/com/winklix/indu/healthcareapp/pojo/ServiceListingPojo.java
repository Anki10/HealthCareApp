package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/19/2018.
 */

public class ServiceListingPojo {

    private String msg;
    private String response;
    private ArrayList<ServiceListingDataPojo> data;



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

    public ArrayList<ServiceListingDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceListingDataPojo> data) {
        this.data = data;
    }


}
