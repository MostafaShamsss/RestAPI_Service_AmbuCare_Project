package com.example.demo.api.repository;

import com.example.demo.api.model.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d")
    List<Driver> getAllDrivers();    Driver findDriverById(Integer id);
//void createDriver(Driver driver);

//void deleteDriver(Integer id);
//
//void updateDriver(Integer id, Driver driver);
//
//Driver getNearestDriver(float driverLocationLat, float driverLocationLong);
}
