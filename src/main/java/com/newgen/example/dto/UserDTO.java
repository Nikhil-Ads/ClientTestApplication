/**
 * 
 */
package com.newgen.example.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.newgen.example.model.Privilege;

import lombok.Data;

/**
 * @author Nikhil Adlakha
 *
 */
@Entity
@Validated
@Data
public class UserDTO {
	
	private String addedBy;

	private String authType;

	private String comments;

	@NotEmpty(message="Display Name cannot be empty")
	@Pattern(regexp="[A-Za-z ]{2,}",message="Display Name is invalid")
	private String displayName;

	private String emailId;

	private Boolean isActive;

	private Boolean isAdmin;

	private String password;

	private Privilege privilege;

	private String tenantId;
}
