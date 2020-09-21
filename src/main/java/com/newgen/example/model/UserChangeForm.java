package com.newgen.example.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class UserChangeForm {
	
	@NotEmpty(message = "Old value is required to validate")
	private String oldValue;
	
	@NotEmpty(message = "New value is required to validate")
	private String newValue;

}
