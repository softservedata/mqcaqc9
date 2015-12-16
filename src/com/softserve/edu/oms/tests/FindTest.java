package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.LoginStartPage;

public class FindTest {
	@DataProvider
	public Object[][] adminUsers() {
		return new Object[][] {
				{ new User("ivanka","horoshko", "iva", "qwerty", "mail@gmail.com") },
		};
	}

	@Test(dataProvider = "adminUsers")
    public void checkExistUsersFind(User existUser) {
        // PreCondition
        AdministrationPage administrationPage = LoginStartPage.load() 
                .successAdminLogin(existUser)
                .gotoAdministration();
        // Test Operation
        administrationPage.searchByLoginName(existUser);
        // TODO Save Actual Result. Preparation for Checking
        // Checking
        Assert.assertEquals(administrationPage.getFirstnameText(), existUser.getFirstname());
        Assert.assertEquals(administrationPage.getLastnameText(), existUser.getLastname());
        // Return to Previous State
        administrationPage.gotoLogout();
        LoginStartPage.quit();
        // TODO Checking
    }

}
