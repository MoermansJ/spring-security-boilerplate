package com.example.demo.util.encryption;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class StringEncodingUtil {
    private final BCryptPasswordEncoder stringEncoder;

    public StringEncodingUtil() {
        this.stringEncoder = new BCryptPasswordEncoder();
    }

    public String encodeString(String rawString) {
        return stringEncoder.encode(rawString);
    }

    public boolean isEqual(String rawString, String encodedString) {
        return stringEncoder.matches(rawString, encodedString);
    }
}
