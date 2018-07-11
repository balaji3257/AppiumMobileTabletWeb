package com.genName.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;

public class AppiumActionsHandler {

	private static final String strReplaceToken = "<<<>>>";
	public WebElement objectToBeIdentified = null;

	private SoftAssert softAssert;

	/*
	 * Desc: Author:
	 */
	@Step
	private void callWait(RemoteWebDriver driver, int iTimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, iTimeOut);
		wait.until(ExpectedConditions.visibilityOf(objectToBeIdentified));
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver) {
		boolean isClicked = false;
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (objectToBeIdentified != null && objectToBeIdentified.isDisplayed()) {
				objectToBeIdentified.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver,
			String strReplacementValues) {
		boolean isClicked = false;
		String strPostReplaceXpath = replaceXpathVariables(strObjectLocator, strReplacementValues);
		try {
			objectToBeIdentified = returnElement(strPostReplaceXpath, getLocType(strPostReplaceXpath), driver);
			if (objectToBeIdentified != null && objectToBeIdentified.isDisplayed()) {
				objectToBeIdentified.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	@Step
	protected boolean click(String strObjectLocator, RemoteWebDriver driver) {
		boolean isClicked = false;
		WebElement clickableObject;
		try {
			clickableObject = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (clickableObject != null) {
				clickableObject.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	@Step
	protected boolean click(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {

		boolean isClicked = false;
		WebElement clickableObject;
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		try {
			clickableObject = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (clickableObject != null) {
				clickableObject.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	private String getLocType(String strObjectLocator) {
		return strObjectLocator.split(":")[0].trim();
	}

	@Step
	protected SoftAssert getSoftAssert() {
		if (softAssert == null) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}

	@Step
	protected String getText(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {
		String strFetchValue = null;

		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (objectToBeIdentified != null) {
				strFetchValue = objectToBeIdentified.getText();
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}

		return strFetchValue;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver) {
		boolean isDisplayedFlag = false;
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (objectToBeIdentified != null) {
				isDisplayedFlag = objectToBeIdentified.isDisplayed();
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return isDisplayedFlag;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {
		boolean isDisplayedFlag = false;
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (objectToBeIdentified != null) {
				isDisplayedFlag = objectToBeIdentified.isDisplayed();
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return isDisplayedFlag;
	}

	@Step
	protected String getAttribute(String strObjectLocator, String strAttributeValue, RemoteWebDriver driver) {
		String strFetchAttributeValue = null;
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (objectToBeIdentified != null) {
				strFetchAttributeValue = objectToBeIdentified.getAttribute(strAttributeValue);
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}

		return strFetchAttributeValue;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected boolean isElementPresent(String Object, RemoteWebDriver driver, int iTimeOut) {
		return false;
	}

	@Step
	private String replaceAllXpathVariable(String strObjectLocator, String[] strReplaceValue) {
		String strPostReplacement = null;
		for (String strSinglReplaceValue : strReplaceValue) {
			replaceXpathVariables(strObjectLocator, strSinglReplaceValue);
		}
		return strPostReplacement;
	}

	@Step
	private String replaceXpathVariables(String strObjectLocator, String strReplaceValue) {
		return strObjectLocator.replace(strReplaceToken, strReplaceValue);
	}

	/*
	 * Desc: Author:
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver) {
		try {
			objectName = objectName.split(":")[1].trim();
			switch (strLocaType.toUpperCase()) {
			case "XPATH":
				objectToBeIdentified = driver.findElement(By.xpath(objectName));
				break;
			case "CSS":
				objectToBeIdentified = driver.findElement(By.cssSelector(objectName));
				break;
			case "ID":
				objectToBeIdentified = driver.findElement(By.id(objectName));
				break;
			case "NAME":
				objectToBeIdentified = driver.findElement(By.name(objectName));
				break;
			default:
				objectToBeIdentified = driver.findElement(By.xpath(objectName));
				break;
			}
		} catch (Exception e) {

		}
		return objectToBeIdentified;
	}

	/*
	 * Desc: Author:
	 */
	@Step
	protected WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver,
			String replacementValue) {
		if (replacementValue == null)
			return returnElement(objectName, strLocaType, driver, new String[0]);
		else
			return returnElement(objectName, strLocaType, driver, new String[] { replacementValue });
	}

	/*
	 * Desc: Author:
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver,String[] replacementValue) {
		String lookupValue = null;
		objectToBeIdentified = null;
		objectName = objectName.split(":")[1].trim();
		lookupValue = replaceAllXpathVariable(objectName, replacementValue);
		switch (strLocaType.toUpperCase()) {
		case "XPATH":
			return driver.findElement(By.xpath(lookupValue));
		case "CSS":
			return driver.findElement(By.cssSelector(lookupValue));
		case "ID":
			return driver.findElement(By.id(lookupValue));
		case "NAME":
			return driver.findElement(By.name(lookupValue));
		default:
			return driver.findElement(By.xpath(lookupValue));
		}
	}
}
