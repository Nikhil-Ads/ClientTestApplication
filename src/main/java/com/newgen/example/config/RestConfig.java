package com.newgen.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.newgen.example.error.handler.RestResponseErrorHandler;

@Configuration
@PropertySource(value = "classpath:validationMessages.properties")
public class RestConfig {
	
	@Bean
	public RestTemplate createRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestResponseErrorHandler());
		return restTemplate;
	}
}
