package com.dincdev.virtualbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class VirtualBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualBookstoreApplication.class, args);
    }

}
