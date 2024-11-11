package com.example.fashion_shop_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FashionShopManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionShopManagementApplication.class, args);
    }

}
