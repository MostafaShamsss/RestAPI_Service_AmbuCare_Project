package com.example.demo.api.repository;

import com.example.demo.api.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    User save(User user);

    boolean existsById(Long id);

    void deleteById(Long id);
}

