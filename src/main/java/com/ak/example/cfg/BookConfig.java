package com.ak.example.cfg;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.ak.example.repo")
@ComponentScan(basePackages = {"com.ak.example.service", "com.ak.example.controller", "com.ak.example.auth"})
@EntityScan(basePackages = "com.ak.example.entity")
public class BookConfig {
	
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
