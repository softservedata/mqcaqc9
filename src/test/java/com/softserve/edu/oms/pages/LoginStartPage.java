package com.softserve.edu.oms.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginStartPage {
	private static volatile LoginStartPage instance = null;
	//
	//private static WebDriver driver;
	private List<WebDriver> driverList = new ArrayList<WebDriver>();
	
	
	private LoginStartPage(){
	}

	public static LoginStartPage get() {
		if (instance == null) {
			synchronized (LoginStartPage.class) {
				if (instance == null) {
					instance = new LoginStartPage();
				}
			}
		}
		return instance;
	}
	
	public static LoginPage load() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		get().driverList.add(driver);
		driver.get("http://ssu-oms:8180/OMS/login.htm");
		return new LoginPage(driver);
	}

	public static LoginPage loadOne() throws InterruptedException {
		if (get().driverList.isEmpty()) {
			return load();
		} else {
			//driverList.get(0).get("http://www.google.com.ua");
			//Thread.sleep(1000);
			get().driverList.get(0).get("http://ssu-oms:8180/OMS/login.htm");
			//Thread.sleep(1000);
			return new LoginPage(get().driverList.get(0));
		}
	}

	public LoginPage logout(){
		driverList.get(0).get("http://ssu-oms:8180/OMS/logout.htm");
		return new LoginPage(driverList.get(0));
	}
	
	public void quit() {
		for (WebDriver driver : driverList) {
			driver.quit();
		}
		//driver.quit(); 
	}
	
}
