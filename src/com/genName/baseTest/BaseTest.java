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
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.genName.config.Utility;
import com.genName.core.AppiumServer;

import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	private DesiredCapabilities cap;
	private List<Map<String, String>> deviceList = new ArrayList<>();
	private static Map<String, String> localDeviceMap = new HashMap<>();
	static AppiumServer appiumServer = new AppiumServer();
	static String serverUrl;

	@BeforeSuite
	public void loadEnvironment() throws Exception {		
		logger.info("Running BeforeSuite");
		Utility.instance().initLogger();
		loadDeviceJsonAsMap();		
		logger.info("Ending BeforeSuite");
	}

	@AfterSuite
	public void clearEnvironment() {
		logger.info("Running AfterSuite");
		if (deviceList != null) {
			localDeviceMap = null;
			deviceList = null;
		}
		
		logger.info("Ending AfterSuite");
	}

	@BeforeClass
	public void testSetUp() {
		logger.info("Running BeforeClass");
		URL url;
		try {
			setDesiredCapabilities();
			serverUrl  = appiumServer.startServer();
			url = new URL(serverUrl);
			driver.set(new RemoteWebDriver(url, cap));
			launchApp();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		logger.info("Ending AfterClass");
	}

	private boolean launchApp() {
		logger.info("Launching App");
		boolean isAppLaunched = false;
		// getWebDriver().get("http://m.kohls.com");
		getWebDriver().get(Utility.instance().getConfigProperty("AppUrl"));
		return isAppLaunched;
	}

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

	private void loadDeviceJsonAsMap() throws Exception {
		deviceList = Utility.instance().deviceJsonAsMap();
		if(deviceList.size()<0) {
			throw new Exception("Error Loading Device.json file");
		}
	}

	@AfterClass
	public void tearDown() {
//		if (driver != null) {
			appiumServer.stopServer();
			localDeviceMap.put("ReUsability", "Yes");
//		}
		
		int PortNumber = Integer.parseInt(serverUrl.split(":")[2].replaceAll("/wd/hub", "") );
		if(appiumServer.checkIfServerIsRunnning(PortNumber) ) {
			appiumServer.stopServer();
		}
	}

	protected RemoteWebDriver getWebDriver() {
		return driver.get();
	}

}
