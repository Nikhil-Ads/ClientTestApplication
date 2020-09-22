package com.newgen.example.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class LoginForm {
	
	@NotEmpty(message="Email must not be blank!!")
	@Pattern(regexp="[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}", message= "emailId must have a prefix, @ and a suffix")
	private String emailId;
	
	@NotEmpty(message="Password must not blank!!")
	@Pattern(regexp=".*", message = "passwordword does not follow set standards")
	private String password;
	
	public LoginForm() {
		
	}
	
	public LoginForm(String emailId,String password) {
		this.emailId=emailId;
		this.password=password;
	}

	/**
	 * @return the emailId
	 */
	public final String getemailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public final void setemailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public final String getpassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setpassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "{"+
					" \"emailId\" : \""+getemailId()+
					"\", \"password\": \""+getpassword()+					
					"\" }";
	}
}
