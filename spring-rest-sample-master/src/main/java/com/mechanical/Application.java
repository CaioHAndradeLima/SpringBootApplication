package com.mechanical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.mechanical.endpoint", "com.mechanical.topic"})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
