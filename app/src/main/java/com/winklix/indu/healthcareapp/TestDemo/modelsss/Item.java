package com.winklix.indu.healthcareapp.TestDemo.modelsss;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Item {

    private  String name;
    private  int id;

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Item(String[] orthoItems) {

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
