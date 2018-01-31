package com.winklix.indu.healthcareapp.modals;

/**
 * Created by harsh on 02/12/2017.
 */

public class Doctor_Modal {

    private String dr_id;
    private String serviceid;
    private String dr_name;
    private String dr_email;
    private String dr_phone;
    private String dr_qualification;
    private String dr_specialization;
    private String dr_experience;
    private String dr_available;


    public Doctor_Modal(String dr_id, String serviceid, String dr_name, String dr_email, String dr_phone,
                        String dr_qualification, String dr_specialization, String dr_experience, String dr_available) {
        this.dr_id = dr_id;
        this.serviceid = serviceid;
        this.dr_name = dr_name;
        this.dr_email = dr_email;
        this.dr_phone = dr_phone;
        this.dr_qualification = dr_qualification;
        this.dr_specialization = dr_specialization;
        this.dr_experience = dr_experience;
        this.dr_available = dr_available;
    }

    public String getDr_id() {
        return dr_id;
    }

    public void setDr_id(String dr_id) {
        this.dr_id = dr_id;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getDr_name() {
        return dr_name;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }

    public String getDr_email() {
        return dr_email;
    }

    public void setDr_email(String dr_email) {
        this.dr_email = dr_email;
    }

    public String getDr_phone() {
        return dr_phone;
    }

    public void setDr_phone(String dr_phone) {
        this.dr_phone = dr_phone;
    }

    public String getDr_qualification() {
        return dr_qualification;
    }

    public void setDr_qualification(String dr_qualification) {
        this.dr_qualification = dr_qualification;
    }

    public String getDr_specialization() {
        return dr_specialization;
    }

    public void setDr_specialization(String dr_specialization) {
        this.dr_specialization = dr_specialization;
    }

    public String getDr_experience() {
        return dr_experience;
    }

    public void setDr_experience(String dr_experience) {
        this.dr_experience = dr_experience;
    }

    public String getDr_available() {
        return dr_available;
    }

    public void setDr_available(String dr_available) {
        this.dr_available = dr_available;
    }
}
