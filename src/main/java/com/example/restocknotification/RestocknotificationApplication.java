package com.example.restocknotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestocknotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestocknotificationApplication.class, args);
    }

}
