package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 09-02-2018.
 */

public class HomevisitTypeListingPojo {


    private String response;
    private String msg;
    private ArrayList<HomeVisitTypeListingDataPojo> data;

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

    public ArrayList<HomeVisitTypeListingDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<HomeVisitTypeListingDataPojo> data) {
        this.data = data;
    }

}
