package com.example.cokingiotvuongkhiem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    private HashMap<String,Devices> dictDevices  = new HashMap<String,Devices>();
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public HashMap<String,Devices> getListDecives() {
        return dictDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevices(String key,Devices device) {
        dictDevices.put(key,device);
    }

    public void addListDevices(ArrayList<Devices> ListDevices){
        for (Devices i : ListDevices){
            dictDevices.put(i.getDeviceName(),i);
        }
    }

    public void updateListDevices(ArrayList<Devices> ListDevices){
        dictDevices.clear();
        for(Devices i: ListDevices){
            dictDevices.put(i.getDeviceName(),i);
        }

    }

    public HashMap<String, Devices> getListDevices() {
        return dictDevices;
    }
}
