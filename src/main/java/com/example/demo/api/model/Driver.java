package com.example.demo.api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String driverName;
    private String driveCarNumber;

    private String driverPhoneNumber;

    private String driverEstimatedTime;

    private float driverLocationLat;

    private float driverLocationLong;

    public Driver() {
    }

    public Driver(int id, String driverName, String driveCarNumber, String driverPhoneNumber, String driverEstimatedTime, float driverLocationLat, float driverLocationLong) {
        this.id = id;
        this.driverName = driverName;
        this.driveCarNumber = driveCarNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverEstimatedTime = driverEstimatedTime;
        this.driverLocationLat = driverLocationLat;
        this.driverLocationLong = driverLocationLong;
    }

    public Driver(String driverName, String driveCarNumber, String driverPhoneNumber, String driverEstimatedTime, float driverLocationLat, float driverLocationLong) {
        this.driverName = driverName;
        this.driveCarNumber = driveCarNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverEstimatedTime = driverEstimatedTime;
        this.driverLocationLat = driverLocationLat;
        this.driverLocationLong = driverLocationLong;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", driveCarNumber='" + driveCarNumber + '\'' +
                ", driverPhoneNumber='" + driverPhoneNumber + '\'' +
                ", driverEstimatedTime='" + driverEstimatedTime + '\'' +
                ", driverLocationLat=" + driverLocationLat +
                ", driverLocationLong=" + driverLocationLong +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriveCarNumber() {
        return driveCarNumber;
    }

    public void setDriveCarNumber(String driveCarNumber) {
        this.driveCarNumber = driveCarNumber;
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