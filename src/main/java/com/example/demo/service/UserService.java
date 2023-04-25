package com.example.demo.service;

import com.example.demo.api.model.User;
import com.example.demo.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setDriverName(user.getDriverName());
            existingUser.setDriverCarNumber(user.getDriverCarNumber());
            existingUser.setDriverPhoneNumber(user.getDriverPhoneNumber());
            existingUser.setDriverEstimatedTime(user.getDriverEstimatedTime());
            existingUser.setDriverLocation(user.getDriverLocation());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }
}

