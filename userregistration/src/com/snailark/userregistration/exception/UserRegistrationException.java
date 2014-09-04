package com.snailark.userregistration.exception;


public class UserRegistrationException extends Exception {
	
	private ExceptionCategory exceptionCategory;
	
	public UserRegistrationException(ExceptionCategory exceptionCategory) {
		this.exceptionCategory = exceptionCategory;
	}
	
	public ExceptionCategory getExceptionCategory() {
		return exceptionCategory;
	}
}
