package com.genName.baseTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.genName.config.Utility;
import com.genName.core.AppiumServer;

import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;

public class BaseTest {

	
	static AppiumServer appiumServer = new AppiumServer();
	protected static Map<String, String> localDeviceMap = new HashMap<>();
	/*
	 * Base class is used to configure the device and other environment Details for the Execution
	 */
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	static String serverUrl;
	private DesiredCapabilities cap;
	private static List<Map<String, String>> deviceList = new ArrayList<>();
	private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	@AfterSuite
	public void flushEnvironmentVariables() {
		if (deviceList != null) {
			localDeviceMap = null;
			deviceList = null;
		}		
		int PortNumber = Integer.parseInt(serverUrl.split(":")[2].replaceAll("/wd/hub", "") );
		if(appiumServer.checkIfServerIsRunnning(PortNumber) ) {
			appiumServer.stopServer();			
		}
		logger.info("Ending TestSuite");
	}

	protected RemoteWebDriver getWebDriver() {
		return driver.get();
	}

	@Step("Launching the App")
	private boolean launchApp() {
		logger.info("Launching App");
		boolean isAppLaunched = false;
		// getWebDriver().get("http://m.kohls.com");
		getWebDriver().get(Utility.instance().getConfigProperty("AppUrl"));
		return isAppLaunched;
	}

	@BeforeSuite
	public void loadDeviceAndLogger() throws Exception {
		Utility.instance().initLogger();
		logger.info("Starting TestSuite");		
		loadDeviceJsonAsMap();		
	}

	@SuppressWarnings("unchecked")
	private void loadDeviceJsonAsMap() throws Exception {
		deviceList = Utility.instance().deviceJsonAsMap();
		logger.info(deviceList.toString());
		if(deviceList.size()<0) {
			throw new Exception("Error Loading Device.json file");
		}
	}

	@Step("Set DesiredCapabilities")
	private void setDesiredCapabilities() {
		if(logger.isInfoEnabled()) {
			logger.info("Setting capabilities..");
		}
		cap = new DesiredCapabilities();

		int i = 0;
		while (deviceList.iterator().hasNext()) {
			// if (deviceList.size() > 0) {
			localDeviceMap = deviceList.get(i);
			cap.setCapability("newCommandTimeout", 60*5);
			if (localDeviceMap.get("os").toString().equalsIgnoreCase("Android")) {
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
				if (localDeviceMap.get("ReUsability").toString().equalsIgnoreCase("Yes")) {
					cap.setCapability(MobileCapabilityType.DEVICE_NAME, localDeviceMap.get("_id"));
					localDeviceMap.put("ReUsability", "No");
					break;
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

	@AfterClass
	public void tearDown() {
		logger.info("AtferClass");
	}
	@AfterMethod
	public void afterTest() {
		logger.info("After Test");
		localDeviceMap.put("ReUsability", "Yes");
	}

	@BeforeClass
	public void testSetUp() {
		logger.info("Starting TestClass");
		URL url;
		try {
			setDesiredCapabilities();
			serverUrl  = "http://0.0.0.0:4723/wd/hub";//appiumServer.startServer();
			url = new URL(serverUrl);
			driver.set(new RemoteWebDriver(url, cap));
			launchApp();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
