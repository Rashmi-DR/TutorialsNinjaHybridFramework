package com.tutorialNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.base.Base;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.SearchPage;

public class SearchTest extends Base {

	HomePage homepage;
	SearchPage searchpage;
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}

	

	@BeforeMethod()
	public void setup() {
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchWithValidProduct() {

		searchpage = homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchpage.displayStatusOfValidHPProduct(), "valid search HP product is not displayed");

	}

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {

		searchpage = homepage.searchForAProduct(dataProp.getProperty("invaildProduct"));
		/*Assert.assertEquals(searchpage.retrieveNoProductMessage(), dataProp.getProperty("NoProductSearchInText"),
				"Search for Invalid product is not displayed");*/
		//this test will fail..
		Assert.assertEquals(searchpage.retrieveNoProductMessage(), "abcd","Search for Invalid product is not displayed");
	}

	//if any one of test Test1,Test 2 fail-> Test 3 will skipped ..
	@Test(priority = 3,dependsOnMethods= {"VerifySearchWithValidProduct","VerifySearchWithInvalidProduct"})
	public void VerifySearchWithoutAnyProductName() {

		searchpage = homepage.clickOnSearchButton();
		Assert.assertEquals(searchpage.retrieveNoProductMessage(), dataProp.getProperty("NoProductSearchInText"),
				"Search for Invalid product is not displayed");

	}

}
