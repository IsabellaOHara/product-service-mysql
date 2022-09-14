package com.qa.product;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ProductServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceMysqlApplication.class, args);
	}

}
