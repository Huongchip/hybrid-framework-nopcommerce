package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_02_Login extends BasePage {

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
	public void TC_01_Login_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", "abc#");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	public void TC_03_Login_Not_Register_Email() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", "abc@gmail.com");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC_04_Login_Email_True_Password_Empty() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", "automation@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_05_Login_Email_True_Password_False() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", "automation@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	
	@Test
	public void TC_06_Login_Login_Sucess() {
		
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
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
