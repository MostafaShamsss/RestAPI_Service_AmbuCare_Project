package com.example.demo.api.controller;
import com.example.demo.api.model.Driver;
import com.example.demo.api.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}
