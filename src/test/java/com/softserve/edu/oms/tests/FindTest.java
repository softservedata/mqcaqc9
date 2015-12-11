package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.LoginStartPage;

public class FindTest {
	
	@AfterClass
	public void oneTimeTearDown() {
		LoginStartPage.get().quit();	
	}

	@DataProvider(parallel = true)
	public Object[][] adminUsers() {
		return new Object[][] {
				{ UserRepository.getExist() },
				{ UserRepository.getCustomer() },
		};
	}

	@Test(dataProvider = "adminUsers")
    public void checkExistUsersFind(IUser existUser) {
        // PreCondition
        AdministrationPage administrationPage = LoginStartPage.load() 
                .successAdminLogin(UserRepository.getAdmin())
                .gotoAdministration();
        // Test Operation
        administrationPage.searchByLoginName(existUser);
        // TODO Save Actual Result. Preparation for Checking
        // Checking
//        System.out.println("TEST: "+administrationPage.getFirstnameText());
        Assert.assertEquals(administrationPage.getFirstnameText(), existUser.getFirstname());
        Assert.assertEquals(administrationPage.getLastnameText(), existUser.getLastname());
        // Return to Previous State
        administrationPage.gotoLogout();
        System.out.println("ThreadId = "+Thread.currentThread().getId());
        //LoginStartPage.quit();
        // TODO Checking
    }

}
