package com.softserve.edu.oms.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginStartPage {
	private static WebDriver driver;
	
	public static LoginPage load() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://ssu-oms:8180/OMS/login.htm");
		return new LoginPage(driver);
	}

	public static void quit() {
		driver.quit();
	}
	
}
