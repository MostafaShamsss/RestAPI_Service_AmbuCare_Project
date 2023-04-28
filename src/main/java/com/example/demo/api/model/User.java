package com.example.demo.api.model;

public class User
{
    int id;
    String driverName;
    String driverCarNumber;
    String driverPhoneNumber;
    String driverEstimatedTime;

    float driverLocationLat;

    float driverLocationLong;

    public User(int id, String driverName, String driverCarNumber, String driverPhoneNumber, String driverEstimatedTime, float driverLocationLat, float driverLocationLong) {
        this.id = id;
        this.driverName = driverName;
        this.driverCarNumber = driverCarNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverEstimatedTime = driverEstimatedTime;
        this.driverLocationLat = driverLocationLat;
        this.driverLocationLong = driverLocationLong;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverCarNumber() {
        return driverCarNumber;
    }

    public void setDriverCarNumber(String driverCarNumber) {
        this.driverCarNumber = driverCarNumber;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public String getDriverEstimatedTime() {
        return driverEstimatedTime;
    }

    public void setDriverEstimatedTime(String driverEstimatedTime) {
        this.driverEstimatedTime = driverEstimatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDriverLocationLat() {
        return driverLocationLat;
    }

    public void setDriverLocationLat(float driverLocationLat) {
        this.driverLocationLat = driverLocationLat;
    }

    public float getDriverLocationLong() {
        return driverLocationLong;
    }

    public void setDriverLocationLong(float driverLocationLong) {
        this.driverLocationLong = driverLocationLong;
    }
}
