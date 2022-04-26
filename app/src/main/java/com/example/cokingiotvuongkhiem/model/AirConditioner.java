package com.example.cokingiotvuongkhiem.model;

public class AirConditioner extends Devices{
    private int setTemp;
    private int currentTemp = 20;
    private int currentHumidity = 60;

    public AirConditioner(int ID, String deviceName, int setTemp, int currentTemp, int currentHumidity) {
        super(ID, deviceName);
        this.setTemp = setTemp;
        this.currentTemp = currentTemp;
        this.currentHumidity = currentHumidity;
    }

    public AirConditioner(int ID, String deviceName) {
        super(ID, deviceName);
    }

    public int getSetTemp() {
        return setTemp;
    }

    public void setSetTemp(int setTemp) {
        this.setTemp = setTemp;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }
}
