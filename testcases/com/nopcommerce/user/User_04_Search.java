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

public class User_04_Search extends BasePage {

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
		sendkeyToElement(driver, "//input[@id='Password']", "1234567");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
	}

	@Test
	public void TC_02_Search_Empty_Data() {
		waitForElementClickable(driver, "//a[text()='Search']");
		clickToElement(driver, "//a[text()='Search']");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");

		Assert.assertEquals(getElementText(driver, "//div[@class='search-results']//div[@class='warning']"),
				"Search term minimum length is 3 characters");
	}

	@Test
	public void TC_03_Data_Not_Exist() {
		sendkeyToElement(driver, "//input[@id='q']", "MacBook Pro 2050");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");

		Assert.assertEquals(getElementText(driver, "//div[@class='search-results']//div[@class='no-result']"),
				"No products were found that matched your criteria.");
	}

	@Test
	public void TC_04_Search_Product_Name() {

		sendkeyToElement(driver, "//input[@id='q']", "Lenovo");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		isElementDisplayed(driver, "//a[text()='Lenovo IdeaCentre 600 All-in-One PC']");
		isElementDisplayed(driver, "//a[text()='Lenovo Thinkpad X1 Carbon Laptop']");
		
	}

	@Test
	public void TC_05_Search_Product_Name() {

		sendkeyToElement(driver, "//input[@id='q']", "ThinkPad X1 Carbon");

		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		
		isElementDisplayed(driver, "//a[text()='Lenovo Thinkpad X1 Carbon Laptop']");
		
	}
	
	@Test
	public void TC_06_Advance_Search_With_Parent_Categories() {
		sendkeyToElement(driver, "//input[@id='q']", "Apple Macbook Pro");
		clickToElement(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		Assert.assertEquals(getElementText(driver, "//div[@class='search-results']//div[@class='no-result']"),
				"No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Advance_Search_With_Sub_Categories() {
		sendkeyToElement(driver, "//input[@id='q']", "Apple Macbook Pro");
		clickToElement(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		
		clickToElement(driver, "//input[@id='isc']");
		
		waitForElementClickable(driver, "//div[@class='buttons']//button[@type='submit']");
		clickToElement(driver, "//div[@class='buttons']//button[@type='submit']");
		isElementDisplayed(driver, "//a[text()='Apple MacBook Pro 13-inch']");
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
