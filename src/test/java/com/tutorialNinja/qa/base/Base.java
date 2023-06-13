package com.tutorialNinja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialNinja.qa.Util.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	//Base class Constructor
	public Base() {
		prop = new Properties();
		File propFile = new File(System.setProperty("user.dir", System.getProperty("user.dir"))
				+ "\\src\\main\\java\\com\\tutorialNinja\\qa\\config\\config.properties\\");
		
		dataProp =new Properties();
		File dataPropFile = new File(System.setProperty("user.dir", System.getProperty("user.dir"))
				+ "\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\testdata.properties\\");
		try{
			FileInputStream datafis=new FileInputStream(dataPropFile);
			dataProp.load(datafis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
		public WebDriver intializeBrowserAndOpenApplicationURL(String browserName) {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("fireFox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
