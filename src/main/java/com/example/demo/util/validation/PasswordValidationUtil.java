package com.example.demo.util.validation;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidationUtil {
    public boolean isValidPassword(String password) {
        return password.trim().length() > 2;
    }
}
