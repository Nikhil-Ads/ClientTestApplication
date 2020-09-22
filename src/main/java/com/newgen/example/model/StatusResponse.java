/**
 * 
 */
package com.newgen.example.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author nikhil.adlakha
 *
 */
@Entity
@Validated
@Data
public class StatusResponse {
	
	@NotEmpty(message="Status Code cannot be empty")
	@Pattern(regexp = "[0-9]+",message="Status code must be a number")
	private String statusCode;
	
	@NotEmpty(message="Status Message cannot be empty")
	@Pattern(regexp="\\w{2,}",message="")
	private String statusMessage;
	
	private String trialDaysRemaining;
}
