package com.example.demo.util.environment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.cdimascio.dotenv.Dotenv;

import static org.junit.jupiter.api.Assertions.*;

class EnvFileLoaderTest {
    private Dotenv dotenv;

    @BeforeEach
    public void setUp() {
        System.getProperties().remove("DATASOURCE_URL");
        System.getProperties().remove("DATASOURCE_USERNAME");
        System.getProperties().remove("DATASOURCE_PASSWORD");
        dotenv = Dotenv.configure().load();
    }

    @Test
    public void testLoadVariables() {
        // Act
        EnvFileLoader.loadVariables();

        // Assert
        assertEquals(dotenv.get("DATASOURCE_URL"), System.getProperty("DATASOURCE_URL"));
        assertEquals(dotenv.get("DATASOURCE_USERNAME"), System.getProperty("DATASOURCE_USERNAME"));
        assertEquals(dotenv.get("DATASOURCE_PASSWORD"), System.getProperty("DATASOURCE_PASSWORD"));
    }
}