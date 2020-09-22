package com.newgen.example.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Entity
@Validated
@Data
public class UserChangeFormDTO {
	
	@NotEmpty(message = "Old Password is required to validate")
	private String oldPassword;
	
	@NotEmpty(message = "New Password is required to update")
	private String newPassword;

}
