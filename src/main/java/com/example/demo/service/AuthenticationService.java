package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.dto.AuthenticationRequest;
import com.example.demo.model.dto.AuthenticationResponse;
import com.example.demo.model.exception.AuthenticationException;
import com.example.demo.repository.UserRepository;

import com.example.demo.security.JwtUtil;
import com.example.demo.util.validation.PasswordValidationUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordValidationUtil passwordValidationUtil;
    //    private StringEncodingUtil stringEncodingUtil;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordValidationUtil passwordValidationUtil,
                                 AuthenticationManager authenticationManager,
                                 JwtUtil jwtUtil,
                                 PasswordEncoder passwordEncoder,
                                 UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordValidationUtil = passwordValidationUtil;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse register(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String dtoUsername = authenticationRequest.getUsername();
        String dtoPassword = authenticationRequest.getPassword();
        Optional<UserEntity> optUserFromDb = userRepository.findUserByUsername(dtoUsername);

        if (optUserFromDb.isPresent())
            throw new AuthenticationException("An account with that username already exists.");

        if (!passwordValidationUtil.isValidPassword(dtoPassword))
            throw new AuthenticationException("The password you entered does not meet the requirements.");

        String encodedPassword = passwordEncoder.encode(dtoPassword);
        userRepository.save(new UserEntity(dtoUsername, encodedPassword));
        return new AuthenticationResponse(dtoUsername, encodedPassword);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        String email = authentication.getName();
        UserEntity user = new UserEntity(email, "");
        String token = jwtUtil.createToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(email, token);

        return authenticationResponse;
    }
}
