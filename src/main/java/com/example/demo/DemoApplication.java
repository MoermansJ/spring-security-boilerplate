package com.example.demo;

import com.example.demo.util.environment.EnvFileLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        EnvFileLoader.loadVariables();
        SpringApplication.run(DemoApplication.class, args);
    }

}
