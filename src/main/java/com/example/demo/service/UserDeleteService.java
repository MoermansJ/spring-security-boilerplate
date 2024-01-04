package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeleteService {
    private UserRepository userRepository;

    public UserDeleteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User delete(long id) {
        Optional<User> optUserFromDb = userRepository.findById(id);

        if (optUserFromDb.isEmpty())
            throw new RuntimeException("There is no user with that id.");

        return userRepository.deleteUserById(id);
    }
}
