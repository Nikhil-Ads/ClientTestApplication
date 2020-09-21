package com.newgen.example.exception;

public class CustomExceptionMessage {
	
	private final int code;
	private final String message;

	public CustomExceptionMessage(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
