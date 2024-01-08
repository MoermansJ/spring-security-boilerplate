package com.example.demo.util.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidationUtilTest {
    private PasswordValidationUtil passwordValidationUtil;

    @BeforeEach
    public void setUp() {
        passwordValidationUtil = new PasswordValidationUtil();
    }

    @Test
    public void isValidPassword_ValidPassword_ReturnsTrue() {
        // Arrange
        String validPassword = "abc";

        // Act
        boolean result = passwordValidationUtil.isValidPassword(validPassword);

        // Assert
        assertTrue(result, "Valid password should return true");
    }

    @Test
    public void isValidPassword_ShortPassword_ReturnsFalse() {
        // Arrange
        String shortPassword = "ab";

        // Act
        boolean result = passwordValidationUtil.isValidPassword(shortPassword);

        // Assert
        assertFalse(result, "Short password should return false");
    }

    @Test
    public void isValidPassword_EmptyPassword_ReturnsFalse() {
        // Arrange
        String emptyPassword = "";

        // Act
        boolean result = passwordValidationUtil.isValidPassword(emptyPassword);

        // Assert
        assertFalse(result, "Empty password should return false");
    }

    @Test
    public void isValidPassword_TrimmedEmptyPassword_ReturnsFalse() {
        // Arrange
        String emptyPassword = "        ";

        // Act
        boolean result = passwordValidationUtil.isValidPassword(emptyPassword);

        // Assert
        assertFalse(result, "Trimmed empty password should return false");
    }
}
