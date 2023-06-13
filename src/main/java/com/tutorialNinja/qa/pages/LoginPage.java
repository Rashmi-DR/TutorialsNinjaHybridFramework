package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(xpath="//input[@value='Login']")
	private WebElement logibButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailNoMatchWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassworld(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickloginButton()
	{
		logibButton.click();
		return new AccountPage(driver);
	}
	public AccountPage login(String emailText,String passwordText)
	{
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		logibButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailNoMatchWarningTextMessage()
	{
		String warningtext=emailNoMatchWarning.getText();
		return warningtext;
	}
}

