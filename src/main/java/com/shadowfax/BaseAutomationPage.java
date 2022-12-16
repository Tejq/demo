package com.shadowfax;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BaseAutomationPage {
	protected AppiumDriver<MobileElement> driver = null;
	protected static final Logger logger = Logger.getLogger(BaseAutomationPage.class);

	public BaseAutomationPage(AppiumDriver<MobileElement> appiumDriver) {
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
	}

	/*
	 * public void verticalScroll() throws InterruptedException {
	 * logger.info("Starting of verticalScroll Method"); Thread.sleep(2000);
	 * Dimension size = driver.manage().window().getSize(); int startX = size.width
	 * / 2; int endX = startX; int startY = (int) (size.height 0.8); int endY =
	 * (int) (size.height 0.4); TouchAction t = new TouchAction(driver);
	 * t.press(PointOption.point(startX,
	 * startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
	 * .moveTo(PointOption.point(endX, endY)).release().perform();
	 * 
	 * logger.info("Ending of verticalScroll Method"); }
	 */
	
	public MobileElement getElementIfVisible(WebDriver driver, MobileElement element) {
		try {
			element = (MobileElement) (new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element)));

		} catch (StaleElementReferenceException se) {
			try {
				element = (MobileElement) new WebDriverWait(driver, 20)
						.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			} catch (Exception e) {
				logger.error("Element unavailable\n" + se.getMessage());
			}
		} catch (Exception e) {
			logger.error("Element unavailable\n" + e.getMessage());
		}
		return element;
	}

	protected void getLongPressAction(By longPressElement) {

		MobileElement holdElement = (MobileElement) driver.findElement(longPressElement);

		AndroidTouchAction t = new AndroidTouchAction(driver);
		t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(holdElement))
				.withDuration(Duration.ofMillis(3000))).release().perform();
	}

	protected void implicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	protected void back() {
		driver.navigate().back();
	}

	protected void pause(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected WebElement findElement(WebElement navigateArowMark, MOBILE_ACTIONS mobileActions) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		switch (mobileActions) {
		case CLICK:
			pause(1000);
			wait.until(ExpectedConditions.elementToBeClickable(navigateArowMark));
			break;
		case VISIBILE:
			wait.until(ExpectedConditions.visibilityOf(navigateArowMark));
			break;
		default:
			wait.until(ExpectedConditions.visibilityOf(navigateArowMark));
		}
		return navigateArowMark;
	}

	protected void clickOnElement(WebElement navigateArowMark) {
		findElement(navigateArowMark, MOBILE_ACTIONS.CLICK).click();
	}

	protected String getText(MobileElement webelement) {
		return findElement(webelement, MOBILE_ACTIONS.VISIBILE).getText();
	}

	protected String getToastText(MobileElement webelement) {
		return findElement(webelement, MOBILE_ACTIONS.TOAST).getText();
	}

	protected void sendKeys(MobileElement webelement, String keys) {
		findElement(webelement, MOBILE_ACTIONS.VISIBILE).sendKeys(keys);
	}

	protected void clear(MobileElement webelement) {
		findElement(webelement, MOBILE_ACTIONS.VISIBILE).clear();
	}

	public enum MOBILE_ACTIONS {
		CLICK, VISIBILE, TOAST
	}

	protected void scrollDown() {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.9);// 0.8
		int endY = (int) (size.height * 0.2);// 0.4
		TouchAction touchAction;
		touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startX, endY))
				.release().perform();
	}

	public void scrollUp() {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.4);
		TouchAction touchAction;
		touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(startX, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(startX, startY)).release().perform();
	}

	public void explicitWait(MobileElement MobileElement) {
		logger.info("Starting of explicitWait method");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(MobileElement));

		logger.info("Ending of explicitWait method");
	}

	public void navigateBack() {
		logger.info("Starting of navigateBack method");

		driver.navigate().back();

		logger.info("Ending of navigateBack method");
	}

	protected void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage,
			WebElement el) {
		final int PRESS_Time = 1000;
		PointOption pointOptionStart = null, pointOptionEnd = null;
		Rectangle rect = el.getRect();
		int edgeBorder = 100;
		pointOptionStart = PointOption.point(rect.x + edgeBorder, rect.y + rect.height / 2);
		pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder, rect.y + rect.height / 2);
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(pointOptionStart).waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_Time)))
				.moveTo(pointOptionEnd).release().perform();
	}

	/*
	 * protected Actions scrolltoView(WebElement element) { Actions actions = new
	 * Actions(driver); actions.moveToElement(element).perform(); return actions; }
	 * 
	 * protected Actions scrollDown() { Actions actions = new Actions(driver);
	 * actions.sendKeys(Keys.PAGE_DOWN).build().perform(); return actions; }
	 * 
	 * protected Actions scrollUp() { Actions actions = new Actions(driver);
	 * actions.sendKeys(Keys.PAGE_UP).build().perform(); return actions; }
	 */
}
