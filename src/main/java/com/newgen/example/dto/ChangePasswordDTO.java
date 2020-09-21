package com.newgen.example.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Entity
@Validated
@Data
public class ChangePasswordDTO {

	@NotEmpty(message="${field}+${email}+${empty_str}")
	@Pattern(regexp="${email_reg}",message="${field}+${email}+${invalid}")
	private String email;
	
	@NotEmpty(message="${field}+${pass}+${empty_str}")
	@Pattern(regexp=".*",message="${field}+${email}+${invalid}")	
	private String pass;
	
	@NotEmpty(message="${field}+${token}+${empty_str}")
	private String token;
}
