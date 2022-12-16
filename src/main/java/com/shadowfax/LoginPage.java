package com.shadowfax;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseAutomationPage {
	public static final Logger logger = Logger.getLogger(LoginPage.class.getName());

	@FindBy(id = "android:id/button1")
	private WebElement btnGrant;
	@FindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
	private WebElement btnAllow;
	@FindBy(id = "in.shadowfax.gandalf.staging:id/welcome_next_fab")
	private WebElement NavigateArowMark;



	public LoginPage(AppiumDriver<MobileElement> AppiumDriver) {
		super(AppiumDriver);
		PageFactory.initElements( new AppiumFieldDecorator(AppiumDriver),this);
	}

	public void clickOnNavigateArowMark() {
		logger.info("Starting of clickOnNavigateArowMark method");
		clickOnElement(NavigateArowMark);
		logger.info("Ending of clickOnNavigateArowMark method");
	}

	public void clickOnbtnGrant() {
		logger.info("Starting of clickOnbtnGrant method");
		clickOnElement(btnGrant);
		logger.info("Ending of clickOnbtnGrant method");
	}

	public void clickOnbtnAllow() {
		logger.info("Starting of clickOnbtnAllow method");
		clickOnElement(btnAllow);
		logger.info("Ending of clickOnbtnAllow method");
	}

}