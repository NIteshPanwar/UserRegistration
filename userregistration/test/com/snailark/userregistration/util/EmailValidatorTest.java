package com.snailark.userregistration.util;

import static org.junit.Assert.*;

import org.junit.Test;



public class EmailValidatorTest {

	EmailValidator emailValidator = new EmailValidator();
	
	@Test
	public void testValidateEmailWithNotNull(){
		boolean isValid = emailValidator.validateEmail("");
		assertFalse("Not Valid email null or empty.", isValid);
	}
	
	@Test
	public void testValidateEmailWithThreeCharDomainName(){
		boolean isValid = emailValidator.validateEmail("npanwar89@gmail.com");
		assertTrue("Valid email with three char domain name is not passed.", isValid);
	}
	
	@Test
	public void testValidateEmailWithTwoCharDomainName(){
		boolean isValid = emailValidator.validateEmail("npanwar@89gmail.eu");
		assertTrue("Valid email with two char domain name is not passed.", isValid);
	}
	
	@Test
	public void testValidateEmailWithAtTheRate(){
		boolean isValid = emailValidator.validateEmail("npanwar@89gmail.com");
		assertTrue("Valid email with two char domain name is not passed.", isValid);
	}

	@Test
	public void testValidateEmailBetweenAtTheRateAndDot(){
		boolean isValid = emailValidator.validateEmail("npanwar@gmail.com");
		assertTrue("Valid email with two char domain name is not passed.", isValid);
	}

	@Test
	public void testValidateEmailStartingChar(){
		boolean isValid = emailValidator.validateEmail("npan@gmail.com");
		assertTrue("Valid email with two char domain name is not passed.", isValid);
	}
	
	@Test
	public void testValidateEmailWithDot(){
		boolean isValid = emailValidator.validateEmail("npan@gmail.com");
		assertTrue("Valid email with two char domain name is not passed.", isValid);
	}
}
