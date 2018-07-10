package com.genName.baseTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.genName.config.Utility;

import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	private DesiredCapabilities cap;
	private List<Map<String, String>> deviceList = new ArrayList<>();
	private static Map<String, String> localDeviceMap = new HashMap<>();

	@BeforeSuite
	public void loadEnvironmentthings() {
		System.out.println("Running BeforeSuite");
		
		loadDeviceJsonAsMap();
		
		System.out.println("Ending BeforeSuite");
	}

	@AfterSuite
	public void clearEnvironment() {
		System.out.println("Running AfterSuite");
		if (deviceList != null) {
			localDeviceMap = null;
			deviceList = null;
		}
		
		System.out.println("Ending AfterSuite");
	}

	@BeforeClass
	public void testSetUp() {
		System.out.println("Running BeforeClass");
		URL url;
		try {
			setDesiredCapabilities();
			url = new URL("http://0.0.0.0:4723/wd/hub");
			driver.set(new RemoteWebDriver(url, cap));
			launchApp();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("Ending AfterClass");
	}

	private boolean launchApp() {
		boolean isAppLaunched = false;
		// getWebDriver().get("http://m.kohls.com");
		getWebDriver().get(Utility.instance().getConfigProperty(""));
		return isAppLaunched;
	}

	private void setDesiredCapabilities() {
		cap = new DesiredCapabilities();

		int i = 0;
		while (deviceList.iterator().hasNext()) {
			// if (deviceList.size() > 0) {
			localDeviceMap = deviceList.get(i);
			if (localDeviceMap.get("os").toString().equalsIgnoreCase("Android")) {
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
				if (localDeviceMap.get("ReUsability").toString().equalsIgnoreCase("Yes")) {
					cap.setCapability(MobileCapabilityType.DEVICE_NAME, localDeviceMap.get("_id"));
					localDeviceMap.put("ReUsability", "No");
				}
			} else {
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
				if (localDeviceMap.get("ReUsability").toString().equalsIgnoreCase("Yes")) {
					cap.setCapability(MobileCapabilityType.UDID, localDeviceMap.get("_id"));
					localDeviceMap.put("ReUsability", "No");
				}
			}
			i++;
		}
	}

	private void loadDeviceJsonAsMap() {
		deviceList = Utility.instance().deviceJsonAsMap();
	}

	@AfterClass
	public void tearDown(RemoteWebDriver driver) {
		if (driver != null) {
			driver.close();
			localDeviceMap.put("ReUsability", "Yes");
		}
	}

	protected RemoteWebDriver getWebDriver() {
		return driver.get();
	}

}
