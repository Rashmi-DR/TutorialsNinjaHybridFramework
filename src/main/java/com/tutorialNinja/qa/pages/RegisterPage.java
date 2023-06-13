package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordconfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesnewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement dupicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailIDWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Action 
	public void enterFirstName(String firstnameText) 
	{
		firstNameField.sendKeys(firstnameText);
	}
	public void enterLastName(String lastnameText)
	{
		lastNameField.sendKeys(lastnameText);
	}
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	public void enterTelephoneNo(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String passwordConfirmText)
	{
		passwordconfirmField.sendKeys(passwordConfirmText);
	}
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public void selectYesNewsLetterOption() 
	{
		yesnewsLetterOption.click();
	}
	public String retrieveDuplicateEmailAddressWarning()
	{
		String dupicateEmailAddressWarningText=dupicateEmailAddressWarning.getText();
		return dupicateEmailAddressWarningText;
	}
	public String retrieveprivacyPolicyWarning() 
	{
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retrieveLastNameWarning()
	{
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveEmailIDWarning()
	{
		String emailIDWarningText=emailIDWarning.getText();
		return emailIDWarningText;
	}
	public String retrievetelephoneWarning()
	{
		String telephoneWarningText=telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrievePasswordWarning()
	{
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
	}

	public AccountSuccessPage registerWithMandatoryField(String firstnameText, String lastnameText, String emailText,
			String telephoneText, String passwordText, String passwordConfirmText) {
		firstNameField.sendKeys(firstnameText);
		lastNameField.sendKeys(lastnameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordconfirmField.sendKeys(passwordConfirmText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);

	}
	public AccountSuccessPage registerWithAllField(String firstnameText, String lastnameText, String emailText,
			String telephoneText, String passwordText, String passwordConfirmText) {
		firstNameField.sendKeys(firstnameText);
		lastNameField.sendKeys(lastnameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordconfirmField.sendKeys(passwordConfirmText);
		yesnewsLetterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);

	}

	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicy, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPasswordWaring)
	{
		
		boolean privacyPolicyWarningStatus=privacyPolicyWarning.getText().contains(expectedPrivacyPolicy);
		boolean firstNameWarningStatus=firstNameWarning.getText().equals(expectedFirstNameWarning);
		boolean lastNameWarningStatus=lastNameWarning.getText().equals(expectedLastNameWarning);
		boolean emailWarningStatus=emailIDWarning.getText().equals(expectedEmailWarning);
		boolean telephoneWarningStatus=telephoneWarning.getText().equals(expectedTelephoneWarning);
		boolean passwordWarningStatus=passwordWarning.getText().equals(expectedPasswordWaring);
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus &&
				emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
}
