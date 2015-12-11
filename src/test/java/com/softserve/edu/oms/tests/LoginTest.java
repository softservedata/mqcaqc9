package com.softserve.edu.oms.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.CustomerHomePage;
import com.softserve.edu.oms.pages.LoginStartPage;
import com.softserve.edu.oms.pages.LoginValidatorPage;

public class LoginTest {
	private SoftAssert softAssert;

	@BeforeClass
	public void oneTimeSetUp() {
		softAssert = new SoftAssert();
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public void oneTimeTearDown() {
		LoginStartPage.get().quit();	
	}

	@AfterMethod
	public void tearDown() {
		LoginStartPage.get().logout();
		System.out.println("@AfterMethod - tearDown");
	}

	@DataProvider
	public Object[][] invalidUsers() {
		return new Object[][] { 
				{ UserRepository.getInvalid() },
		};
	}

	@Test(dataProvider = "invalidUsers")
	public void checkInvalidLogin(IUser invalidUser) throws InterruptedException {
		// Test Operation
		LoginValidatorPage loginValidatorPage = LoginStartPage.loadOne()
				.unsuccessfulLogin(invalidUser);
		// Check
//		Assert.assertTrue(loginValidatorPage.getValidatorText().startsWith(
//				LoginValidatorPage.START_VALIDATOR_MESSAGE));
		softAssert.assertTrue(loginValidatorPage.getValidatorText().startsWith(
				LoginValidatorPage.START_VALIDATOR_MESSAGE));
		Thread.sleep(2000);
		// Return
		//LoginStartPage.quit();
	}

	@DataProvider
	public Object[][] adminUsers() {
		return new Object[][] {
				{ UserRepository.getAdmin() },
		};
	}

	@Test(dataProvider = "adminUsers")
	public void checkAdminLogin(IUser adminUser) throws InterruptedException {
		// Test Operation
		AdminHomePage adminHomePage = LoginStartPage.loadOne()
				.successAdminLogin(adminUser);
		int i = 1 / 0;
		System.out.println(i);
		// Check
//		Assert.assertEquals(adminHomePage.getFirstnameText(),
//				adminUser.getFirstname());
//		Assert.assertEquals(adminHomePage.getLastnameText(),
//				adminUser.getLastname());
		softAssert.assertEquals(adminHomePage.getFirstnameText(),
				adminUser.getFirstname());
		softAssert.assertEquals(adminHomePage.getLastnameText(),
				adminUser.getLastname());
//		Assert.assertEquals(adminHomePage.getLastnameText(),
//				adminUser.getLastname()+"1");
		Thread.sleep(2000);
		// Return
		adminHomePage.gotoLogout();
		//LoginStartPage.quit();
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] customerUsers() {
		return new Object[][] {
				{ UserRepository.getCustomer() },
		};
	}

	@Test(dataProvider = "customerUsers")
	public void checkCustomerLogin(IUser customerUser) throws InterruptedException {
		// Test Operation
		CustomerHomePage customerHomePage = LoginStartPage.loadOne()
				.successCustomerLogin(customerUser);
		// Check
		System.out.println("Customer = "+customerHomePage.getOrdering().getText());
//		Assert.assertEquals(customerHomePage.getFirstnameText(),
//				customerUser.getFirstname());
//		Assert.assertEquals(customerHomePage.getLastnameText(),
//				customerUser.getLastname());
		softAssert.assertEquals(customerHomePage.getFirstnameText(),
				customerUser.getFirstname());
		softAssert.assertEquals(customerHomePage.getLastnameText(),
				customerUser.getLastname());
		Thread.sleep(2000);
		// Return
		customerHomePage.gotoLogout();
		//LoginStartPage.quit();
	}

}
