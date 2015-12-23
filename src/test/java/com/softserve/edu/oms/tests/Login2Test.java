package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.ListUtils;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.LoginStartPage;

public class Login2Test {

	@DataProvider
	public Object[][] allAdminUsers() {
		return (new ListUtils()).toMultiArray(UserRepository.getAdminCSV());
	}

	//@Test
	public void checkAdminLogin2() throws InterruptedException {
		System.out.println("DONE\n" + UserRepository.getAdminCSV());
	}

	@Test(dataProvider = "allAdminUsers")
	public void checkAdminLogin(IUser adminUser) throws InterruptedException {
		// Test Operation
		AdminHomePage adminHomePage = LoginStartPage.loadOne()
				.successAdminLogin(adminUser);
		//
		// Check
		Assert.assertEquals(adminHomePage.getFirstnameText(),
				adminUser.getFirstname());
		Assert.assertEquals(adminHomePage.getLastnameText(),
				adminUser.getLastname());
		// Assert.assertEquals(adminHomePage.getLastnameText(),
		// adminUser.getLastname()+"1");
		Thread.sleep(2000);
		// Return
		adminHomePage.gotoLogout();
		// LoginStartPage.quit();
	}

}
