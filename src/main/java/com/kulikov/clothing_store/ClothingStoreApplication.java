package com.kulikov.clothing_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClothingStoreApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ClothingStoreApplication.class);
		app.setAdditionalProfiles("filler");
		app.run(args);
		//SpringApplication.run(ClothingStoreApplication.class, args);
	}

}
