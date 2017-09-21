package com.intertec.usernamegenerator;

import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {
	
	@Test
	public void validateNonExistingUsername() {
		UserValidator validator = new UserValidator();
		Result result1 = validator.checkUserName("nonexistingusername");
		Result result2 = validator.checkUserName("nonexistingusername");
		
		Assert.assertTrue(result1.isStatus());
		Assert.assertFalse(result2.isStatus());	
	}
	
	@Test
	public void validateExistingUsername() {
		UserValidator validator = new UserValidator();
		Result result1 = validator.checkUserName("erickarley");
		
		Assert.assertNotNull(result1);
		Assert.assertFalse(result1.isStatus());
		Assert.assertNotNull(result1.getSuggestions());
		Assert.assertEquals(result1.getSuggestions().size(), 14);
	}
	
	@Test
	public void validateRestrictedWords() {
		UserValidator validator = new UserValidator();
		Result result1 = validator.checkUserName("crackisbad");
		Result result2 = validator.checkUserName("cannabis");
		
		Assert.assertFalse(result1.isStatus());
		Assert.assertFalse(result2.isStatus());	
	}

	@Test(expected = IllegalArgumentException.class) 
	public void usernameShorterThanExpected() {
		UserValidator validator = new UserValidator();
		validator.checkUserName("erick");
		
	}
	
	@Test
	public void validateExceptionMessage() { 
		UserValidator validator = new UserValidator();
		try {
			validator.checkUserName("erick");
			Assert.fail("An exception was expected");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(),"Username should at least contain 6 characters");
		}
	}
}
