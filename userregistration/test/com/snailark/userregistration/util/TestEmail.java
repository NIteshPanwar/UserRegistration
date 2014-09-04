package com.snailark.userregistration.util;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestEmail {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(EmailValidatorTest.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.getException());
		}
		System.out.println(result.wasSuccessful());
	}
	
}
