package com.genName.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

public class AppiumActionsHandler {

	private static final String TOKEN = "<<<>>>";
	private static final String NUMBERED_TOKEN = "<<<{num}>>>";
	public WebElement objectToBeIdentified = null;
	private SoftAssert softAssert;

	protected SoftAssert getSoftAssert() {
		if (softAssert == null) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}

	protected boolean isElementPresent(String Object, RemoteWebDriver driver, int iTimeOut) {
		return false;
	}

	protected WebElement returnElement(String objectName, RemoteWebDriver driver, String replacementValue) {
		if (replacementValue == null)
			return returnElement(objectName, driver, new String[0]);
		else
			return returnElement(objectName, driver, new String[] { replacementValue });
	}

	private static String replaceAllTokens(String originalValue, String[] tokenArray) {
		String returnValue = originalValue;
		int currentPosition = 0;
		for (String tokenValue : tokenArray)
			returnValue = replaceToken(returnValue, tokenValue, ++currentPosition);

		return returnValue;
	}

	private static String replaceToken(String originalValue, String tokenValue, int position) {
		String useToken = TOKEN;
		int indexOf = originalValue.indexOf(TOKEN);

		if (indexOf == -1) {
			useToken = NUMBERED_TOKEN.replace("{num}", Integer.toString(position));
			indexOf = originalValue.indexOf(useToken);

		}

		if (indexOf != -1) {
			return originalValue.substring(0, indexOf) + tokenValue
					+ originalValue.substring(indexOf + useToken.length());
		}

		return originalValue;

	}

	protected WebElement returnElement(String objectName, RemoteWebDriver driver, String[] replacementValue) {
		String lookupValue = null;
		objectToBeIdentified = null;
		// ORLookup orLookup = PropertyReader.instance().getElement(objectName,
		// driver.getOsType());

		// /Need to construct method to return locator as String
		// String lookupValue = orLookup.getOrValue();
		lookupValue = lookupValue.replace("'", "\"");
		if (replacementValue != null && replacementValue.length > 0)
			lookupValue = replaceAllTokens(lookupValue, replacementValue);

		switch ("") {
		case "XPATH":
			return driver.findElement(By.xpath(lookupValue));
		case "CSS":
			return driver.findElement(By.cssSelector(lookupValue));
		case "ID":
			return driver.findElement(By.id(lookupValue));
		case "NAME":
			return driver.findElement(By.name(lookupValue));
		case "VISUAL":
			return driver.findElement(By.linkText(lookupValue));
		default:
			return driver.findElement(By.xpath(lookupValue));
		}
	}
}
