package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.pages.LoginStartPage;
import com.softserve.edu.oms.pages.LoginValidatorPage;

public class LoginTest {
	
	@DataProvider
	public Object[][] invalidUsers() {
		return new Object[][] {
				{ new User("ivanka","horoshko", "iva1", "qwerty1", "mail@gmail.com") },
		};
	}

	@Test(dataProvider = "invalidUsers")
	public void checkInvalidLogin(User invalidUser) {
		// Test Operation
		LoginValidatorPage loginValidatorPage = LoginStartPage.load()
				.unsuccessfulLogin(invalidUser);
		// Check
		Assert.assertTrue(loginValidatorPage.getValidatorText()
				.startsWith(LoginValidatorPage.START_VALIDATOR_MESSAGE));
		// Return
	    LoginStartPage.quit();
		}
}
