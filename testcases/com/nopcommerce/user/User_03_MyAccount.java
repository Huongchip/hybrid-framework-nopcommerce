package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import org.openqa.selenium.support.ui.Select;

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
	public void TC_01_Login() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendkeyToElement(driver, "//input[@id='Email']", "automationfc.vn@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
	}

	public void TC_02_MyAccount_Customer_Info() {

		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");

		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "1");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "1");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1999");

		clickToElement(driver, "//input[@id='gender-female']");
		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", "automationfc.vn@gmail.com");
		sendkeyToElement(driver, "//input[@id='Company']", "Automation FC");

		waitForElementClickable(driver, "//button[@id='save-info-button']");
		clickToElement(driver, "//button[@id='save-info-button']");

		Assert.assertEquals(getElementText(driver, "//p[@class='content']"),
				"The customer info has been updated successfully.");

	}

	
	public void TC_03_MyAccount_Address() {
		waitForElementClickable(driver, "//a[text()='Addresses']");
		clickToElement(driver, "//a[text()='Addresses']");

		waitForElementClickable(driver, "//button[text()='Add new']");
		clickToElement(driver, "//button[text()='Add new']");

		sendkeyToElement(driver, "//input[@id='Address_FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='Address_LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Address_Email']", "automationfc.vn@gmail.com");
		sendkeyToElement(driver, "//input[@id='Address_Company']", "Automation FC");

		selectItemInDefaultDropdown(driver, "//select[@id='Address_CountryId']", "82");
		sendkeyToElement(driver, "//input[@id='Address_City']", "Da Nang");
		sendkeyToElement(driver, "//input[@id='Address_Address1']", "123/04 Le Lai");
		sendkeyToElement(driver, "//input[@id='Address_Address2']", "234/05 Hai Phong");
		sendkeyToElement(driver, "//input[@id='Address_ZipPostalCode']", "550000");
		sendkeyToElement(driver, "//input[@id='Address_PhoneNumber']", "123456789");
		sendkeyToElement(driver, "//input[@id='Address_FaxNumber']", "0987654321");

		waitForElementClickable(driver, "//button[@class='button-1 save-address-button']");
		clickToElement(driver, "//button[@class='button-1 save-address-button']");

		Assert.assertEquals(getElementText(driver, "//p[@class='content']"),
				"The new address has been added successfully.");
	}

	@Test
	public void TC_04_MyAccount_Change_Password() {
		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");

		waitForElementClickable(driver, "//a[text()='Change password']");
		clickToElement(driver, "//a[text()='Change password']");

		sendkeyToElement(driver, "//input[@id='OldPassword']", "1234567");
		sendkeyToElement(driver, "//input[@id='NewPassword']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmNewPassword']", "123456");

		waitForElementClickable(driver, "//button[@class='button-1 change-password-button']");
		clickToElement(driver, "//button[@class='button-1 change-password-button']");


		sendkeyToElement(driver, "//input[@id='Email']", "automation@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");

		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		sendkeyToElement(driver, "//input[@id='Password']", "123456");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");

		String actualUrl = "https://demo.nopcommerce.com/";
		String expectedUrl = getPageUrl(driver);

		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test
	public void TC_05_MyAccount_My_Product_Reviews() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
