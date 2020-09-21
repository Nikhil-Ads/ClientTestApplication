package com.newgen.example.dto;

import javax.validation.constraints.NotEmpty;

import com.newgen.example.model.ChildType;

public abstract class ChildDTO {
	
	@NotEmpty(message = "Child Type must not be Empty")
	private final ChildType type;
	
	public ChildDTO(ChildType type) {
		this.type=type;
	}
	
	public final ChildType getType() {
		return type;
	}
	

}
