package com.newgen.example;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class ClientTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTestApplication.class, args);
	}
	
	@Bean
	public Validator createValidatorBean() {
		ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
		return factory.getValidator();		
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(new String[]{"classpath:common-messages"});
		return messageSource;
	}

}
