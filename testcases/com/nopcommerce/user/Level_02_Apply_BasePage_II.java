package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_II {
  WebDriver driver;
  String emailAddress;
  
  //declare(khai bao)
  BasePage basePage;
  //BasePage: Class
  //basePage: Object
  
  String projectPath =  System.getProperty("user.dir");
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		//initial(khoi tao)
		basePage = BasePage.getBasePageObject();
		
		emailAddress = "afc@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
  }
	
  @Test
  public void TC_01_Register_Empty_Data() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	 
	  basePage.waitForElementClickable(driver, "//button[id='register-button']");
	  basePage.clickToElement(driver, "//button[id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
  }
 
  @Test
  public void TC_02_Register_Invalid_data() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", "123@456#");
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
  }

  @Test
  public void TC_03_Register_Sucess() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  basePage.clickToElement(driver, "//a[@class='ico-logout'");
	  
  }
  
  @Test
  public void TC_04_Register_Existing_Email() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");

  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_6_Chars() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\\nmust have at least 6 characters");
  }
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "PThe password and confirmation password do not match.");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
