package com.codedsalis.chowdify.productservice;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary() {
		Dotenv dotenv = Dotenv.load();
		return new Cloudinary(dotenv.get("CLOUDINARY_URL"));
	}

}
