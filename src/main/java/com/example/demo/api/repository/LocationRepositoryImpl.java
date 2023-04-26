package com.example.demo.api.repository;

import com.example.demo.api.model.Location;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final RestTemplate restTemplate;

    public LocationRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Location findById(Long id) {
        String url = "http://localhost:8080/locations/" + id;
        ResponseEntity<Location> response = restTemplate.getForEntity(url, Location.class);
        return response.getBody();
    }

    @Override
    public List<Location> findAll() {
        String url = "http://localhost:8080/locations";
        ResponseEntity<List<Location>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Location>>() {}
        );
        return response.getBody();
    }

    @Override
    public Location save(Location location) {
        String url = "http://localhost:8080/locations";
        return restTemplate.postForObject(url, location, Location.class);
    }

    @Override
    public void update(Location location) {
        String url = "http://localhost:8080/locations/" + location.getId();
        restTemplate.put(url, location);
    }

    @Override
    public void deleteById(Long id) {
        String url = "http://localhost:8080/locations/" + id;
        restTemplate.delete(url);
    }
}


