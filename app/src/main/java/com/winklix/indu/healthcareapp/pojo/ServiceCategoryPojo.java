package com.winklix.indu.healthcareapp.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class ServiceCategoryPojo {

  private String message;
  private ArrayList<ServiceCategoryDataPojo> data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ArrayList<ServiceCategoryDataPojo> getData() {
    return data;
  }

  public void setData(ArrayList<ServiceCategoryDataPojo> data) {
    this.data = data;
  }


}
