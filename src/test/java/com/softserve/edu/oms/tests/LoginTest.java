package com.softserve.edu.oms.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.ReporterWrapper;
import com.softserve.edu.oms.data.ReporterWrapper.ReporterLevels;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.CustomerHomePage;
import com.softserve.edu.oms.pages.LoginStartPage;
import com.softserve.edu.oms.pages.LoginValidatorPage;

public class LoginTest {
	public static final Logger logger = LoggerFactory
			.getLogger(LoginTest.class);
	private boolean testPass = true;
	private boolean consoleDuplicate = true;
	private SoftAssert softAssert;

	@BeforeClass
	public void oneTimeSetUp() {
		softAssert = new SoftAssert();
		// System.out.println("@BeforeClass - oneTimeSetUp");
		logger.info("@BeforeClass - oneTimeSetUp()");
		Reporter.log("<br><p>@BeforeClass - oneTimeSetUp()</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
	}

	@AfterClass
	public void oneTimeTearDown() {
		LoginStartPage.get().quit();
		logger.info("@AfterClass - oneTimeTearDown()");
		Reporter.log("<br><p>@AfterClass - oneTimeTearDown()</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
	}

	@BeforeMethod
	public void setUp() {
		testPass = false;
		// System.out.println("@BeforeMethod - setUp");
		logger.info("@BeforeMethod - setUp");
		Reporter.log("<br><p>@BeforeMethod - setUp</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
	}

	@AfterMethod
	public void tearDown() {
		String filePath;
		LoginStartPage.get().logout();
		// System.out.println("@AfterMethod - tearDown");
		logger.info("@AfterMethod - tearDown()");
		Reporter.log("<br><p>@AfterMethod - tearDown()</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		if (!testPass) {
			filePath = (new ReporterWrapper()).captureAndSaveScreen();
			logger.error("@AfterMethod - TEST FAILURE, filePath=" + filePath);
			Reporter.log("<br><p>[ERROR] <font color='red'>@AfterMethod - <b>TEST FAILURE</b></font>filePath="
							+ filePath + "</p>",
					ReporterLevels.ERROR_LEVEL.getLevel(), consoleDuplicate);
		}
	}

	@DataProvider
	public Object[][] invalidUsers() {
		return new Object[][] { { UserRepository.getInvalid() }, };
	}

	@Test(dataProvider = "invalidUsers")
	public void checkInvalidLogin(IUser invalidUser)
			throws InterruptedException {
		logger.info("@Test - checkInvalidLogin() start");
		Reporter.log("<br><p>@Test - checkInvalidLogin() start</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		// Test Operation
		LoginValidatorPage loginValidatorPage = LoginStartPage.loadOne()
				.unsuccessfulLogin(invalidUser);
		// Check
		// Assert.assertTrue(loginValidatorPage.getValidatorText().startsWith(
		// LoginValidatorPage.START_VALIDATOR_MESSAGE));
		softAssert.assertTrue(loginValidatorPage.getValidatorText().startsWith(
				LoginValidatorPage.START_VALIDATOR_MESSAGE));
		Thread.sleep(2000);
		// Return
		// LoginStartPage.quit();
		logger.info("@Test - checkInvalidLogin() done");
		Reporter.log("<br><p>@Test - checkInvalidLogin() done</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		testPass = true;
	}

	@DataProvider
	public Object[][] adminUsers() {
		return new Object[][] { { UserRepository.getAdmin() }, };
	}

	@Test(dataProvider = "adminUsers")
	public void checkAdminLogin(IUser adminUser) throws InterruptedException {
		logger.info("@Test - checkAdminLogin() start");
		Reporter.log("<br><p>@Test - checkAdminLogin() start</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		// Test Operation
		AdminHomePage adminHomePage = LoginStartPage.loadOne()
				.successAdminLogin(adminUser);
		int i = 1 / 0;
		System.out.println(i);
		// Check
		// Assert.assertEquals(adminHomePage.getFirstnameText(),
		// adminUser.getFirstname());
		// Assert.assertEquals(adminHomePage.getLastnameText(),
		// adminUser.getLastname());
		softAssert.assertEquals(adminHomePage.getFirstnameText(),
				adminUser.getFirstname());
		softAssert.assertEquals(adminHomePage.getLastnameText(),
				adminUser.getLastname());
		// Assert.assertEquals(adminHomePage.getLastnameText(),
		// adminUser.getLastname()+"1");
		Thread.sleep(2000);
		// Return
		adminHomePage.gotoLogout();
		// LoginStartPage.quit();
		softAssert.assertAll();
		logger.info("@Test - checkAdminLogin() done");
		Reporter.log("<br><p>@Test - checkAdminLogin() done</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		testPass = true;
	}

	@DataProvider
	public Object[][] customerUsers() {
		return new Object[][] { { UserRepository.getCustomer() }, };
	}

	@Test(dataProvider = "customerUsers")
	public void checkCustomerLogin(IUser customerUser)
			throws InterruptedException {
		logger.info("@Test - checkCustomerLogin() start");
		Reporter.log("<br><p>@Test - checkCustomerLogin() start</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		// Test Operation
		CustomerHomePage customerHomePage = LoginStartPage.loadOne()
				.successCustomerLogin(customerUser);
		// Check
		System.out.println("Customer = "
				+ customerHomePage.getOrdering().getText());
		// Assert.assertEquals(customerHomePage.getFirstnameText(),
		// customerUser.getFirstname());
		// Assert.assertEquals(customerHomePage.getLastnameText(),
		// customerUser.getLastname());
		softAssert.assertEquals(customerHomePage.getFirstnameText(),
				customerUser.getFirstname());
		softAssert.assertEquals(customerHomePage.getLastnameText(),
				customerUser.getLastname());
		Thread.sleep(2000);
		// Return
		customerHomePage.gotoLogout();
		// LoginStartPage.quit();
		logger.info("@Test - checkCustomerLogin() done");
		Reporter.log("<br><p>@Test - checkCustomerLogin() done</p>",
				ReporterLevels.INFO_LEVEL.getLevel(), consoleDuplicate);
		testPass = true;
	}

}
