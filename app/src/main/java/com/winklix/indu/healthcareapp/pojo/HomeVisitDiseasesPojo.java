package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by dell on 09-02-2018.
 */

public class HomeVisitDiseasesPojo {

    private String response;
    private String msg;
    private ArrayList<HomeVisitDiseasesdataPojo> data;

    public ArrayList<HomeVisitDiseasesdataPojo> getData() {
        return data;
    }

    public void setData(ArrayList<HomeVisitDiseasesdataPojo> data) {
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
