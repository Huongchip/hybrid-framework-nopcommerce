package pageObject;

import org.openqa.selenium.WebDriver;

import pageUIs.RegisterPageUI;
import commons.BasePage;

public class RegisterPageObject extends BasePage{

	WebDriver driver;
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}

	public String getErrorMessageAtFirstnameTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrorMessageAtLastnameTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrorMessageAtEmailnameTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrorMessageAtPasswordTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void inputToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputToEmailnameTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
		
	}

	public String getRegisterSuccessMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clickToLogoutLink() {
		// TODO Auto-generated method stub
		
	}

}
