package com.example.demo.api.model;

public class User
{
    String driverName;
    String driverCarNumber;
    String driverPhoneNumber;
    String driverEstimatedTime;
    Location driverLocation;


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

    public Location getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(Location driverLocation) {
        this.driverLocation = driverLocation;
    }
}
