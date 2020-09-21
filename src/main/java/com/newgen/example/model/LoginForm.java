package com.newgen.example.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class LoginForm {
	
	@NotEmpty(message="")
	@Pattern(regexp="[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}", message= "Email must have a prefix, @ and a suffix")
	private String email;
	
	@NotEmpty(message="")
	@Pattern(regexp=".*", message = "Password does not follow set standards")
	private String pass;
	
	public LoginForm(String email,String pass) {
		this.email=email;
		this.pass=pass;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pass
	 */
	public final String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public final void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "{"+
					" \"emailId\" : "+getEmail()+
					",\"password\": "+getPass()+					
					"}";
	}
}
