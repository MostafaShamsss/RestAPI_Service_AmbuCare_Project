package com.example.demo.api.repository;

import com.example.demo.api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public UserRepositoryImpl(RestTemplate restTemplate,
                              @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public Optional<User> findById(Long id) {
        String url = apiUrl + "/users/" + id;
        try {
            User user = restTemplate.getForObject(url, User.class);
            return Optional.ofNullable(user);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            } else {
                throw ex;
            }
        }
    }

    @Override
    public User save(User user) {
        String url = apiUrl + "/users";
        User savedUser = restTemplate.postForObject(url, user, User.class);
        return savedUser;
    }

    @Override
    public boolean existsById(Long id) {
        String url = apiUrl + "/users/" + id;
        try {
            restTemplate.getForObject(url, User.class);
            return true;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        String url = apiUrl + "/users/" + id;
        restTemplate.delete(url);
    }
}

