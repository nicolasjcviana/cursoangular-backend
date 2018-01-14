package com.algaworks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.api.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyMain {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyMain.class, args);
	}
	
}
