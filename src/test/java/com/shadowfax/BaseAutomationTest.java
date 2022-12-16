package com.shadowfax;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseAutomationTest {

	private static final Logger logger = Logger.getLogger(BaseAutomationTest.class.getName());
	protected static Map<String, AndroidDriver<MobileElement>> driversMap = new HashMap<String, AndroidDriver<MobileElement>>();
	protected static Properties testDataProp = null;

	protected static Properties expectedAssertionsProp = null;

	protected String environment = System.getProperty("environmentType");

	protected AndroidDriver<MobileElement> driver = null;

	public enum SCREEN_ID {
	}

	@BeforeSuite
	public void initTestData() {

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;
			try {
				testDataReader = new FileReader("src/main/resources/testdata.properties");
				assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					//assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	protected synchronized void initMobileDriverForLogin() throws MalformedURLException {

		logger.info("Starting of method initMobileDriver");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", System.getProperty("deviceName"));

		cap.setCapability("udid", System.getProperty("udid"));

		cap.setCapability("platformName", System.getProperty("platformName"));

		cap.setCapability("platformVersion", System.getProperty("platformVersion"));

		cap.setCapability("appPackage", System.getProperty("appPackage"));

		cap.setCapability("appActivity", System.getProperty("appActivity"));

		cap.setCapability("noReset", System.getProperty("noReset"));
		cap.setCapability("noReset", false);
		cap.setCapability("fullReset", false);
		if (environment.equalsIgnoreCase("local")) {

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		} else if (environment.equalsIgnoreCase("mobile lab")) {

			driver = new AndroidDriver<MobileElement>(new URL("http://192.168.217.78:4723/wd/hub"), cap);
		}

		driversMap.put(System.getProperty("udid"), driver);

		logger.info("Ending of method initMobileDriver");

	}

	protected synchronized void initMobileDriver() throws MalformedURLException {

		logger.info("Starting of method initMobileDriver");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", System.getProperty("deviceName"));

		cap.setCapability("udid", System.getProperty("udid"));

		cap.setCapability("platformName", System.getProperty("platformName"));

		cap.setCapability("platformVersion", System.getProperty("platformVersion"));

		cap.setCapability("appPackage", System.getProperty("appPackage"));

		cap.setCapability("appActivity", System.getProperty("appActivity"));

		cap.setCapability("noReset", System.getProperty("noReset"));
		cap.setCapability("noReset", true);
		cap.setCapability("fullReset", false);
		if (environment.equalsIgnoreCase("local")) {

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		} else if (environment.equalsIgnoreCase("mobile lab")) {

			driver = new AndroidDriver<MobileElement>(new URL("http://192.168.217.78:4723/wd/hub"), cap);

		}

		driversMap.put(System.getProperty("udid"), driver);

		logger.info("Ending of method initMobileDriver");

	}

	protected synchronized AndroidDriver<MobileElement> getMobileDriver(String driverKey) throws Exception {
		logger.info("Starting of method getMobileDriver");

		AndroidDriver<MobileElement> driver = null;
		driver = (AndroidDriver<MobileElement>) driversMap.get(driverKey);

		// Use existing driver
		if (driver == null) {
			logger.error("Driver not initialized, Please call initDriver Method. Before calling getDriver ");
			throw new Exception("Drivier not initialized");
		}
		// Otherwise create new driver
		logger.info("Ending of method getMobileDriver");
		return driver;
	}

	public static String getRandomString(final int length) {
		final StringBuilder buffer = new StringBuilder();
		final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int charactersLength = chars.length();

		for (int i = 0; i < length; i++) {
			final double index = Math.random() * charactersLength;
			buffer.append(chars.charAt((int) index));
		}
		return buffer.toString();
	}

	protected synchronized void quitMobileDriver(String driverKey) {
		logger.info("Starting of method quitDriver in BaseInstallationProcessTest");
		AndroidDriver<MobileElement> driver = null;
		driver = (AndroidDriver<MobileElement>) driversMap.get(driverKey);
		try {
			if (driver != null) {
				driver.quit();
				driver = null;

			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			driver = null;
		}
		logger.info("Ending of method quitDriver in BaseInstallationProcessTest");
	}

	public WebDriver getChildWebDriver() {
		return driver;
	}

}