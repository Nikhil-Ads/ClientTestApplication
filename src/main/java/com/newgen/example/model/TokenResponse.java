package com.newgen.example.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Validated
@AllArgsConstructor
@Getter
@Setter
public class TokenResponse {
	
	@NotEmpty(message="Access Token cannout be empty")
	private String token;
	
	@NotEmpty(message="Refresh Token cannout be empty")
	private String refreshToken;
}
