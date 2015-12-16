package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAdmin {
	@Test
	public void testAdm() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://ssu-oms:8180/OMS/login.htm");
		WebElement login = driver.findElement(By.name("j_username"));
		WebElement password = driver.findElement(By.name("j_password"));
		WebElement button = driver.findElement(By.name("submit"));

		login.sendKeys("iva");
		password.sendKeys("qwerty");
		//Thread.sleep(1000);
		button.click();
		String s = driver.findElement(By.xpath("//td[text()='First name']/following-sibling::td"))
				.getText();
		// Assert.assertEquals(s, "horoshko");
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='spec']")).click();
		//Thread.sleep(1000);
		driver.quit();
		Assert.assertEquals(s, "horoshko");
	}
}
