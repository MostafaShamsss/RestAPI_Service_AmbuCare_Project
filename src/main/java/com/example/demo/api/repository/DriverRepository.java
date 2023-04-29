package com.example.demo.api.repository;

import com.example.demo.api.model.Driver;
import com.example.demo.api.model.status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d")
    List<Driver> getAllDrivers();
    Driver findDriverById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO driver(driver_name, drive_car_number, driver_phone_number, driver_estimated_time, driver_location_lat, driver_location_long) VALUES (:driverName, :driveCarNumber, :driverPhoneNumber, :driverEstimatedTime, :driverLocationLat, :driverLocationLong)", nativeQuery = true)
    void insertData(@Param("driverName") String driverName, @Param("driveCarNumber") String driveCarNumber, @Param("driverPhoneNumber") String driverPhoneNumber, @Param("driverEstimatedTime") String driverEstimatedTime, @Param("driverLocationLat") float driverLocationLat, @Param("driverLocationLong") float driverLocationLong);

    @Query("SELECT d FROM Driver d WHERE d.driverStatus = 'Available'")
    List<Driver> findAvailableDrivers();

    @Modifying
    @Query("UPDATE Driver d SET d.driverStatus = :status WHERE d.id = :id")
    @Transactional
    int updateDriverStatus(@Param("id") Integer id, @Param("status") status driverStatus);


}
