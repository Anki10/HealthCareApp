package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 12-02-2018.
 */

public class HomevisitorlocationPojo {

    private String response;
    private String msg;
    private ArrayList<HomevisitorlocationDataPojo> data;

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

    public ArrayList<HomevisitorlocationDataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<HomevisitorlocationDataPojo> data) {
        this.data = data;
    }


}
