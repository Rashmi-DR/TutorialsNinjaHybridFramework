package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//objects
	WebDriver driver;
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(name="search")
	private WebElement SearchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenu.click();
		LoginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage selectRegisterOption() {
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropMenu.click();
		RegisterOption.click();
		return new RegisterPage(driver);
		
	}
	public void enterProductIntoSearchBoxField(String productText)
	{
		SearchBoxField.sendKeys(productText);
	}
	public SearchPage clickOnSearchButton() 
	{
		searchButton.click();	
		return new SearchPage(driver);
	}
	public SearchPage searchForAProduct(String productText) 
	{
		SearchBoxField.sendKeys(productText);
		searchButton.click();	
		return new SearchPage(driver);
	}
}
