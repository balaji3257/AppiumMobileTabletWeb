package com.genName.baseTest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.genName.Util.Utility;

import io.appium.java_client.remote.MobileCapabilityType;


public class BaseTest {

	Utility util = new Utility();
	private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	private DesiredCapabilities cap;
	private List<Map<String,String>> deviceList = new ArrayList<>();

	@BeforeClass
	public void testSetUp() {
		URL url;
		try {
			setDesiredCapabilities();
			loadDeviceJsonAsMap();
			url = new URL("http://0.0.0.0:4723/wd/hub");
			driver.set(new RemoteWebDriver(url,cap));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	private  void setDesiredCapabilities() {
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "d83bcc68");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
	}

	public void loadDeviceJsonAsMap() {
	
	}

	@AfterClass
	public void tearDown(RemoteWebDriver driver) {
		if (driver != null)
			driver.close();
	}
	
	protected RemoteWebDriver getWebDriver() {
		return driver.get();
	}
	
	
	
	
}
