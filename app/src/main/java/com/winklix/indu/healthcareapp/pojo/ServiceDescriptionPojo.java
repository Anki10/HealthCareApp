package com.winklix.indu.healthcareapp.pojo;

/**
 * Created by dell on 23-01-2018.
 */

public class ServiceDescriptionPojo {

    private ServiceDescriptionDataPojo data;
    private String msg;
    private String response;

    public ServiceDescriptionDataPojo getData() {
        return data;
    }

    public void setData(ServiceDescriptionDataPojo data) {
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
