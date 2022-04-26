package com.example.cokingiotvuongkhiem.model;

public class Devices {
    private int ID;
    private String deviceName;
    private int imgID;
    private boolean checked;
    private int status;

    @Override
    public String toString() {
        return "Devices{" +
                "ID=" + ID +
                ", deviceName='" + deviceName + '\'' +
                ", imgID=" + imgID +
                ", checked=" + checked +
                ", status=" + status +
                '}';
    }

    public Devices(int ID, String deviceName, int imgID) {
        this.deviceName = deviceName;
        this.imgID = imgID;
        this.ID = ID;
    }

    public Devices(int ID, String deviceName) {
        this.deviceName = deviceName;
        this.ID = ID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getImgID() {
        return imgID;
    }

    public void setCourseName(String courseName) {
        this.deviceName = courseName;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setStatus(String statusText) {
        this.status = status;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getStatus() {
        return status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}