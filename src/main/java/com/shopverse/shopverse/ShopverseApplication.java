package com.shopverse.shopverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.shopverse.shopverse")
public class ShopverseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopverseApplication.class, args);
    }
}
