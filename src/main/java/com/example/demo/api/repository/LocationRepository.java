package com.example.demo.api.repository;

import com.example.demo.api.model.Location;

import java.util.List;

public interface LocationRepository {

    Location findById(Long id);

    List<Location> findAll();

    Location save(Location location);

    void update(Location location);

    void deleteById(Long id);

}


