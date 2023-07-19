package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register {
  WebDriver driver;
  String emailAddress;
  String projectPath =  System.getProperty("user.dir");
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
		driver.get("https://demo.nopcommerce.com/");
  }
	
  @Test
  public void TC_01_Register_Empty_Data() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("button#register-button"));
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
  }
 
  @Test
  public void TC_02_Register_Invalid_data() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys("123@456#");
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
  }

  @Test
  public void TC_03_Register_Sucess() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	  driver.findElement(By.cssSelector("a.ico-logout")).click();
	  
  }
  
  @Test
  public void TC_04_Register_Existing_Email() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_6_Chars() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
