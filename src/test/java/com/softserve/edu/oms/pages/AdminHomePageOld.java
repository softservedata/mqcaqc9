package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.oms.data.User;

public class AdminHomePageOld {
	private WebDriver driver;
	//
	private WebElement firstname;
	private WebElement lastname;
	private WebElement role;
	private WebElement administration;
	private WebElement logout;

	public AdminHomePageOld(WebDriver driver) {
		this.driver = driver;
		//
		this.firstname=driver.findElement(By.xpath("//td[text()='First name']/following-sibling::td"));
		this.lastname=driver.findElement(By.xpath("//td[text()='Last name']/following-sibling::td"));
		this.role=driver.findElement(By.xpath("//td[text()='Role']/following-sibling::td"));
		this.administration=driver.findElement(By.partialLinkText("Administration"));
		this.logout=driver.findElement(By.xpath("//a[@href='/OMS/logout.htm']"));
	}

	// Get Elements
	public WebElement getFirstname() {
		return this.firstname;
	}
	
	public WebElement getLastname() {
		return this.lastname;
	}

	public WebElement getRole() {
		return this.role;
	}
	
	public WebElement getAdministration() {
		return this.administration;
	}

	public WebElement getLogout() {
		return this.logout;
	}
	
	public String getFirstnameText() {
		return this.firstname.getText();
	}
	
	public String getLastnameText() {
		return this.lastname.getText();
	}

	public String getRoleText() {
		return this.role.getText();
	}

	// Set Data
	public void clickAdministration() {
		this.administration.click();
	}
	
	public void clickLogout() {
		this.logout.click();
	}
	
	// Business Logic
	public AdministrationPage gotoAdministration() {
		clickAdministration();
	    return new AdministrationPage(driver);
	}

	public LoginPage gotoLogout() {
		clickLogout();
		return new LoginPage(driver);
	}

}
