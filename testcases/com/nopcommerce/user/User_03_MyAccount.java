package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_03_MyAccount extends BasePage {

	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();

		emailAddress = "afc@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		openPageUrl(driver, "https://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_Login_Sucess() {
		
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", "automation@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		String actualUrl="https://demo.nopcommerce.com/";
		String expectedUrl= getPageUrl(driver);
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test
	public void TC_01_MyAccount_Customer_Info() {
		waitForElementClickable(driver, "//div[@class='header-links']//a[@class='ico-account']");
		clickToElement(driver, "//div[@class='header-links']//a[@class='ico-account']");

		clickToElement(driver, "//input[@id='gender-female']");
		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", "automationfc.vn@gmail.com");
		sendkeyToElement(driver, "//input[@id='Company']", "Automation FC");

	}

	@Test
	public void TC_02_MyAccount_Address() {
		
	}
	
	public void TC_03_MyAccount_Change_Password() {
	
	}
	
	@Test
	public void TC_04_MyAccount_My_Product_Reviews() {
	
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
