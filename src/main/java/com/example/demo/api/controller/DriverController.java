package com.example.demo.api.controller;

import com.example.demo.api.model.Driver;
import com.example.demo.api.model.LocationDTO;
import com.example.demo.api.model.status;
import com.example.demo.api.repository.DriverRepository;
import com.example.demo.interfaces.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRepository;
    @RateLimit(limit = 1, timeout = 1) // limit to 5 requests per minute
    @GetMapping("/drivers")
    public List<Driver> getAll() {
        return (List<Driver>) driverRepository.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Optional<Driver> getByID(@PathVariable String id) {
        Integer ID = Integer.parseInt(id);
        return driverRepository.findById(ID);

    }

    @PostMapping("/drivers/nearest")
    public Optional<Driver> getByNearestDriver(@RequestBody LocationDTO location) {
        float driverLocationLat = location.getLatitude();
        float driverLocationLong = location.getLongitude();

        Driver closestDriver = null;
        double minDistance = Double.MAX_VALUE;
        List<Driver> drivers = (List<Driver>) driverRepository.findAvailableDrivers();

        for (Driver driver : drivers) {
            double distance = calculateDistance(driverLocationLat, driverLocationLong,
                    driver.getDriverLocationLong(), driver.getDriverLocationLat());
            if (distance < minDistance) {
                minDistance = distance;
                closestDriver = driver;
            }
        }

        return Optional.ofNullable(closestDriver);
    }

    @PostMapping("/drivers/confirm/{id}")
    public String confirmRequest(@PathVariable String id) {
        int rowsAffected = driverRepository.updateDriverStatus(Integer.valueOf(id), status.Unavailable);
        if (rowsAffected > 0) {
            return "Successful";
        } else {
            return "Not successful";
        }
    }

    @PostMapping("/drivers/cancel/{id}")
    public String cancelRequest(@PathVariable String id) {
        int rowsAffected = driverRepository.updateDriverStatus(Integer.valueOf(id), status.Available);
        if (rowsAffected > 0) {
            return "Successful";
        } else {
            return "Not successful";
        }
    }


    /**
     * Create a new driver
     */
    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody Driver newDriver) {
        return driverRepository.save(newDriver);
    }

    private float calculateDistance(double requestLat, double requestLong, double driverLong, double driverLat) {
        double distance = Math.sqrt(Math.pow((driverLong - requestLong),
                2) + Math.pow((driverLat - requestLat), 2));

        return (float) distance;

    }
}
