package com.snailark.userregistration.exception;

public enum ExceptionCategory {
	
	SYSTEM("There is system error, please contact system administrator."), INVALID_EMAIL("Please specify valid email."), EMAIL_ALREADY_EXISTS("User with given email address already exists.");
	
	private String message;
	private ExceptionCategory(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
