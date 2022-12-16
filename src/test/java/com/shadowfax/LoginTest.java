package com.shadowfax;

import java.net.MalformedURLException;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginTest extends BaseAutomationTest {

	public static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	private LoginPage loginpage;
	
	@BeforeClass
	public void initClass() throws Exception {
		logger.info("Starting of initClass method in LoginTest");
		this.initMobileDriverForLogin();
		loginpage = new LoginPage(getMobileDriver(System.getProperty("udid")));
		logger.info("Ending of initClass method in LoginTest");
	}
	
	@Test(priority = 1)
	@Description("Verify Login Page")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify Login Page")
	public void verifyLoginPage() throws InterruptedException {
		logger.info("Starting of verifyLoginPage method");
		
		loginpage.clickOnbtnGrant();
		loginpage.clickOnbtnAllow();
		loginpage.clickOnNavigateArowMark();
		
		logger.info("Starting of verifyLoginPage method");
	}

	@AfterClass
	public void quitdriver() {
		driver.quit();
	}
}