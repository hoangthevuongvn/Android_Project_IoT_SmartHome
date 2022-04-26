package com.example.cokingiotvuongkhiem.model;

import java.util.HashMap;

public class Home {
    private HashMap<String,Object> dictDevices  = new HashMap<String,Object>();
    private String name;

    public Home(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getDictDevices() {
        return dictDevices;
    }

    public void setDictDevices(HashMap<String, Object> dictDevices) {
        this.dictDevices = dictDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevices(String key, Object obj){
        dictDevices.put(key, obj);
    }

}
