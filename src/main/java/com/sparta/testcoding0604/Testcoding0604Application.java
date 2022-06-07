package com.sparta.testcoding0604;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Testcoding0604Application {

    public static void main(String[] args) {
        SpringApplication.run(Testcoding0604Application.class, args);
    }

}
