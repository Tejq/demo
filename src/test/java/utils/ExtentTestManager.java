package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();
	private static String destDir = (System.getenv("CRC_RESOURCES") + "/screenshots/courtchecker/");
	
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	public static synchronized void failTest(ExtentTest test, String testName, String deviceName, String screenId) {

		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		String testResult = "<a href=\"http://www.github.com\\?testName=" + test + "\"> Log Bug :" + testName + "</a>";
		test.error(MarkupHelper.createLabel(testResult, ExtentColor.RED));
		try {
			test.addScreenCaptureFromPath(
					destDir+ "/" +deviceName+ "/" +screenId+".png");
			test.addScreenCaptureFromPath(
					destDir+ "/" +deviceName+ "/" +screenId+"_COMP.png");
			test.addScreenCaptureFromPath(
					destDir+ "/" +deviceName+ "/" +screenId+"_RESULT.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}