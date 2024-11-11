package com.example.fashion_shop_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
public class FashionShopManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionShopManagementApplication.class, args);
	}
=======
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FashionShopManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionShopManagementApplication.class, args);
    }
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870

}
