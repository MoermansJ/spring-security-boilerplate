package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.AuthenticationRequest;
import com.example.demo.model.dto.AuthenticationResponse;
import com.example.demo.model.exception.AuthenticationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.validation.PasswordValidationUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordValidationUtil passwordValidationUtil;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordValidationUtil passwordValidationUtil) {
        this.userRepository = userRepository;
        this.passwordValidationUtil = passwordValidationUtil;
    }

    public AuthenticationResponse register(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String dtoUsername = authenticationRequest.getUsername();
        String dtoPassword = authenticationRequest.getPassword();
        Optional<User> optUserFromDb = userRepository.findUserByUsername(dtoUsername);

        if (optUserFromDb.isPresent())
            throw new AuthenticationException("An account with that username already exists.");

        if (!passwordValidationUtil.isValidPassword(dtoPassword))
            throw new AuthenticationException("The password you entered does not meet the requirements.");

        User newUser = new User(dtoUsername, dtoPassword);
        return new AuthenticationResponse(dtoUsername, dtoPassword);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String dtoUsername = authenticationRequest.getUsername();
        String dtoPassword = authenticationRequest.getPassword();
        Optional<User> optUserFromDb = userRepository.findUserByUsername(dtoUsername);

        if (optUserFromDb.isEmpty())
            throw new AuthenticationException("That username is not registered.");

        User userFromDb = optUserFromDb.get();

        if (!dtoPassword.equals(userFromDb.getPassword()))
            throw new AuthenticationException("The password you entered is incorrect.");

//        String token = jwtUtil.createToken(userFromDb, "USER");

        return new AuthenticationResponse(dtoUsername, dtoPassword);
    }
}
