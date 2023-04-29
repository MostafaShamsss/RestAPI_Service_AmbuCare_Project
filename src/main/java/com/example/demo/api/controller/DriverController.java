package com.example.demo.api.controller;
import com.example.demo.api.model.Driver;
import com.example.demo.api.model.LocationDTO;
import com.example.demo.api.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.sqrt;

@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRespository;
    @GetMapping("/drivers")
    public List<Driver> getAll(){
        return (List<Driver>) driverRespository.findAll();
    }
    @GetMapping("/drivers/{id}")
    public Optional<Driver> getByID(@PathVariable String id){
        Integer ID = Integer.parseInt(id);
        return driverRespository.findById(ID);

    }
    @PostMapping("/drivers/nearest")
    public Optional<Driver> getByNearestDriver (@RequestBody LocationDTO location){
        float driverLocationLat = location.getLatitude();
        float driverLocationLong = location.getLongitude();

        Driver closestDriver = null;
        double minDistance = Double.MAX_VALUE;
        List<Driver> drivers = (List<Driver>) driverRespository.findAll();

        for (Driver driver : drivers) {
            double distance = calculateDistance(driverLocationLat,driverLocationLong,
                    driver.getDriverLocationLong(),driver.getDriverLocationLat());
            if (distance < minDistance) {
                minDistance = distance;
                closestDriver =driver;
}
        }

        return Optional.ofNullable(closestDriver);
    }
    private float calculateDistance(double requestLat,double requestLong,double driverLong, double driverLat){
        double distance = Math.sqrt(Math.pow((driverLong - requestLong),
                2) + Math.pow((driverLat - requestLat),2));

    return (float) distance;

    }
}
