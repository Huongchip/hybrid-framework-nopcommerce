package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;

public class Level_03_Page_Object extends BasePage {
  WebDriver driver;
  String emailAddress;
  String projectPath =  System.getProperty("user.dir");
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		emailAddress = "afc@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		openPageUrl(driver, "https://demo.nopcommerce.com/");
  }
	
  @Test
  public void TC_01_Register_Empty_Data() {
	  
	  System.out.println("Home Page - Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("Register Page - Click to Register Button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	 
//	  waitForElementClickable(driver, "//button[id='register-button']");
//	  clickToElement(driver, "//button[id='register-button']");
	  
	  System.out.println("Step-03: Verify error message");
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Email name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");

	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
  }
 
  @Test
  public void TC_02_Register_Invalid_data() {
	  
	  System.out.println("Home Page - Click to Register link");
	  homePage.clickToRegisterLink();
	  
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  
	  System.out.println("Step02: Input to required fields");
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailnameTextbox("123@456#");
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
//	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
//	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
//	  sendkeyToElement(driver, "//input[@id='Email']", "123@456#");
//	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
//	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
//	  waitForElementClickable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  System.out.println("Register Page - Click to Register Button");
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(), "Wrong email");
  }

  @Test
  public void TC_03_Register_Sucess() {
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  System.out.println("Home Page - Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailnameTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
	  
//	  waitForElementClickable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  registerPage.clickToLogoutLink();
	  
  }
  
//  @Test
//  public void TC_04_Register_Existing_Email() {
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
//	  
//	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
//	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
//	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
//	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
//	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
//	  
//	  waitForElementClickable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
//	  
//	  Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
//
//  }
//  
//  @Test
//  public void TC_05_Register_Password_Less_Than_6_Chars() {
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
//	  
//	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
//	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
//	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
//	  sendkeyToElement(driver, "//input[@id='Password']", "123");
//	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
//	  
//	  waitForElementClickable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
//	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\\nmust have at least 6 characters");
//  }
//  
//  @Test
//  public void TC_06_Register_Invalid_Confirm_Password() {
//	  waitForElementClickable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
//	  
//	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
//	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
//	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
//	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
//	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
//	  
//	  waitForElementClickable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
//	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "PThe password and confirmation password do not match.");
//  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  HomePageObject homePage;
  RegisterPageObject registerPage;
}
