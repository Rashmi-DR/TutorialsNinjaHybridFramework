package com.tutorialNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.Util.Utilities;
import com.tutorialNinja.qa.base.Base;
import com.tutorialNinja.qa.pages.AccountSuccessPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.RegisterPage;

public class RegisterTest extends Base {

	RegisterPage registerpage ;
	AccountSuccessPage accountsuccesspage;
	
	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod()
	public void setup() {
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		registerpage=homepage.navigateToRegisterPage();
	
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyRegisteringOnlyMandatoryField() {

		accountsuccesspage = registerpage.registerWithMandatoryField(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.genarateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSccessPageHeading(),
				dataProp.getProperty("accountSuccessfullyCreated"), "Account page is not successfully created");

	}

	@Test(priority = 2)
	public void VerifyRegisteringProvidingAllFields() {

		accountsuccesspage=registerpage.registerWithAllField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.genarateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreated"),
				"Account page is not successfully created");

	}

	@Test(priority = 3)
	public void VerifyRegisteringProvidingAlreadyExistingEmail() {
		
		registerpage.registerWithAllField(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerpage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),
				"warning Your registering With duplicate Email ID");

	}

	@Test(priority = 4)
	public void VerifyRegisteringAccountWithOutFillingAnyDetails() {

		registerpage.clickContinueButton();
		Assert.assertTrue(registerpage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"),
				dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"),
				dataProp.getProperty("emailIDWarning"), dataProp.getProperty("telephoneWarning"),
				dataProp.getProperty("passwordWarning")));
	}

}
