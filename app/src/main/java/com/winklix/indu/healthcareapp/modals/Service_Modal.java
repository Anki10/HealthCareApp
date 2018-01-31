package com.winklix.indu.healthcareapp.modals;

/**
 * Created by harsh on 27/11/2017.
 */

public class Service_Modal {

    private String service_id;
    private String service_name;
    private String service_location;
    private String service_img;


    public Service_Modal(String service_id, String service_name, String service_location, String service_img) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_location = service_location;
        this.service_img = service_img;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_location() {
        return service_location;
    }

    public void setService_location(String service_location) {
        this.service_location = service_location;
    }

    public String getService_img() {
        return service_img;
    }

    public void setService_img(String service_img) {
        this.service_img = service_img;
    }
}


