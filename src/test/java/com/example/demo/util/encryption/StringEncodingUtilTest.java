package com.example.demo.util.encryption;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StringEncodingUtilTest {
    @Test
    public void testEncodeString() {
        // Arrange
        String rawString = "rawString";
        BCryptPasswordEncoder mockEncoder = mock(BCryptPasswordEncoder.class);
        when(mockEncoder.encode(rawString)).thenReturn("hashedString");

        StringEncodingUtil stringEncodingUtil = new StringEncodingUtil();
        ReflectionTestUtils.setField(stringEncodingUtil, "stringEncoder", mockEncoder);

        // Act
        String encodedString = stringEncodingUtil.encodeString(rawString);

        // Assert
        assertEquals("hashedString", encodedString);
        verify(mockEncoder, times(1)).encode(rawString);
    }

    @Test
    public void testIsEqual_True() {
        // Arrange
        String rawString = "rawString";
        String encodedString = "$2a$10$abcdefghijabcdefghijabcdefghij";

        BCryptPasswordEncoder mockEncoder = mock(BCryptPasswordEncoder.class);
        when(mockEncoder.matches(rawString, encodedString)).thenReturn(true);

        StringEncodingUtil stringEncodingUtil = new StringEncodingUtil();
        ReflectionTestUtils.setField(stringEncodingUtil, "stringEncoder", mockEncoder);

        // Act
        boolean result = stringEncodingUtil.isEqual(rawString, encodedString);

        // Assert
        assertTrue(result);
        verify(mockEncoder, times(1)).matches(rawString, encodedString);
    }

    @Test
    public void testIsEqual_False() {
        // Arrange
        String rawString = "rawString";
        String encodedString = "$2a$10$abcdefghijabcdefghijabcdefghij";

        BCryptPasswordEncoder mockEncoder = mock(BCryptPasswordEncoder.class);
        when(mockEncoder.matches(rawString, encodedString)).thenReturn(false);

        StringEncodingUtil stringEncodingUtil = new StringEncodingUtil();
        ReflectionTestUtils.setField(stringEncodingUtil, "stringEncoder", mockEncoder);

        // Act
        boolean result = stringEncodingUtil.isEqual(rawString, encodedString);

        // Assert
        assertFalse(result);
        verify(mockEncoder, times(1)).matches(rawString, encodedString);
    }
}