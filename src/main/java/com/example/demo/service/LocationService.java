package com.example.demo.service;

import com.example.demo.api.model.Location;
import com.example.demo.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Location location) {
        Optional<Location> existingLocation = Optional.ofNullable(locationRepository.findById(location.getId()));
        if (existingLocation.isPresent()) {
            return locationRepository.save(location);
        } else {
            return null;
        }
    }


    public boolean deleteLocation(Long id) {
        Optional<Location> existingLocation = Optional.ofNullable(locationRepository.findById(id));
        if (existingLocation.isPresent()) {
            locationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}

