package com.prueba.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BackendPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPruebaApplication.class, args);
	}

}
