package com.tutorialNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.Util.Utilities;
import com.tutorialNinja.qa.base.Base;
import com.tutorialNinja.qa.pages.AccountPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.LoginPage;

public class LoginTest extends Base {

	LoginPage loginpage;

	// base class constructor is called
	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod()
	public void setup() {
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginPage();
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentailsSupplier")
	public void verifyLoginWithValidCredentails(String email, String password) {

		AccountPage accountpage = loginpage.login(email, password);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditAccountInformationOptions(),
				"Edit your accoutn information");

	}

	@DataProvider(name = "validCredentailsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentails() {

		loginpage.login(Utilities.genarateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailNoMatchWarningTextMessage()
				.contains(dataProp.getProperty("emailNoMatchWarning")), "Excepted waring message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailadressesAndValidPwd() {

		loginpage.login(Utilities.genarateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginpage.retrieveEmailNoMatchWarningTextMessage()
				.contains(dataProp.getProperty("emailNoMatchWarning")), "Excepted waring message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailadressesAndInvalidPwd() {

		loginpage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailNoMatchWarningTextMessage()
				.contains(dataProp.getProperty("emailNoMatchWarning")), "Excepted waring message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginpage.clickloginButton();
		Assert.assertTrue(loginpage.retrieveEmailNoMatchWarningTextMessage()
				.contains(dataProp.getProperty("emailNoMatchWarning")), "Excepted waring message is not displayed");

	}

}
