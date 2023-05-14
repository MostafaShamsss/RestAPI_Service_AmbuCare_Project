package com.example.demo.api.model;


import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String driverName;
    private String driveCarNumber;

    private String driverPhoneNumber;

    @Enumerated(EnumType.STRING)
    private status driverStatus = status.Available;
    private float driverLocationLat;

    private float driverLocationLong;

    public Driver() {
    }

    public Driver(String driverName, String driveCarNumber, String driverPhoneNumber, status driverStatus, float driverLocationLat, float driverLocationLong) {
        this.driverName = driverName;
        this.driveCarNumber = driveCarNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverStatus = driverStatus;
        this.driverLocationLat = driverLocationLat;
        this.driverLocationLong = driverLocationLong;
    }

    public Driver(int id, String driverName, String driveCarNumber, String driverPhoneNumber,
                  status driverStatus, float driverLocationLat, float driverLocationLong)
    {
        this.id = id;
        this.driverName = driverName;
        this.driveCarNumber = driveCarNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverStatus = driverStatus;
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
                ", driverStatus=" + driverStatus +
                ", driverLocationLat=" + driverLocationLat +
                ", driverLocationLong=" + driverLocationLong +
                '}';
    }

    public status getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(status driverStatus) {
        this.driverStatus = driverStatus;
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