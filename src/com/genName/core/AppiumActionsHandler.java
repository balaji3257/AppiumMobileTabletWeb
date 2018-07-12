package com.genName.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;

public class AppiumActionsHandler {

	private static final String strReplaceToken = "<<<>>>";
	private WebElement objectToBeIdentified = null;

	private SoftAssert softAssert;

	/*
	 * Desc : Check the existence of the given object and click the element .
	 * Author: return : boolean
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver) throws Exception {
		boolean isClicked = false;
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
					objectToBeIdentified.click();
				isClicked = true;
			}
		} catch (Exception e) {
			throw new Exception("Non existence of the element : " + strObjectLocator);
		}
		return isClicked;
	}

	/*
	 * Desc : Check the existence of the given object and click the element based on
	 * the given dynamic xpath value. Author: return : boolean
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver,
			String strReplacementValues) {
		System.out.println(" ");
		boolean isClicked = false;
		WebElement webElement = null;
		String strPostReplaceXpath = replaceXpathVariables(strObjectLocator, strReplacementValues);
		System.out.println("Replaced Xpath = " + strPostReplaceXpath);
		String strLocatorContext = getLocType(strPostReplaceXpath);
		try {
			webElement = returnElement(strPostReplaceXpath, strLocatorContext, driver);
			if (webElement != null && webElement.isDisplayed()) {
				webElement.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	/*
	 * Desc : Check the element based on the given xpath value. Author: return :
	 * boolean
	 */
	@Step
	protected boolean click(String strObjectLocator, RemoteWebDriver driver) {
		boolean isClicked = false;
		WebElement clickableObject;
		String strLocatorType = getLocType(strObjectLocator);
		try {
			clickableObject = returnElement(strObjectLocator, strLocatorType, driver);
			if (clickableObject != null) {
				clickableObject.click();
				isClicked = true;
			}
		} catch (Exception e) {

		}
		return isClicked;
	}

	/*
	 * Desc : Check the element based on the given dynamic xpath value. Author:
	 * return : boolean
	 */
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
	 * Desc : Return Locator type Author: return : String value
	 */
	@Step
	private String getLocType(String strObjectLocator) {
		String strLocatorType = strObjectLocator.split("mmmm")[0].trim();
		System.out.println("LocatorType =" + strLocatorType);
		return strLocatorType;
	}

	/*
	 * Desc : Returns softAssert Object. Author: return : soft assert object
	 */
	@Step
	protected SoftAssert getSoftAssert() {
		if (softAssert == null) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}

	/*
	 * Desc : Get the text of the given element Author: return : String value
	 */
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
	 * Desc: Check the existence of the given element Author: Return: boolean
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver) {
		boolean isDisplayedFlag = false;
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (waitElementForVisibility( objectToBeIdentified, 10 ,driver)) {
				isDisplayedFlag = true;
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return isDisplayedFlag;
	}

	private Boolean waitElementForVisibility( WebElement el, int seconds ,RemoteWebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(el));
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * Desc: Check the existence of the given element Author: Return: boolean
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {
		boolean isDisplayedFlag = false;
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		try {
			objectToBeIdentified = returnElement(strObjectLocator, getLocType(strObjectLocator), driver);
			if (waitElementForVisibility(objectToBeIdentified, 10 ,driver)) {
				isDisplayedFlag = true;
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return isDisplayedFlag;
	}

	/*
	 * Desc: Return the value of the given attribute dynamically Author: Return:
	 * String
	 */
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
	 * Desc: Replace the given String value in the Object string Author: Return:
	 * String
	 */
	@Step
	private String replaceAllXpathVariable(String strObjectLocator, String[] strReplaceValue) {
		String strPostReplacement = null;
		for (String strSinglReplaceValue : strReplaceValue) {
			strPostReplacement = replaceXpathVariables(strObjectLocator, strSinglReplaceValue);
		}
		return strPostReplacement;
	}

	/*
	 * Desc: Replace the given String value in the Object string Author: Return:
	 * String
	 */
	@Step
	private String replaceXpathVariables(String strObjectLocator, String strReplaceValue) {
		String strRepalcedLocator = strObjectLocator.replace(strReplaceToken, strReplaceValue);
		return strRepalcedLocator;
	}

	/*
	 * Desc: Returns the element of the given locator. Author: Return: WebElement
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver) {
		try {
			objectName = objectName.split("mmmm")[1].trim();
			System.out.println(objectName);
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
	 * Desc: Returns the element of the given locator dynamically. Author: Return:
	 * WebElement
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
	 * Desc: Returns the element of the given locator dynamically. Author: Return:
	 * WebElement
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver,
			String[] replacementValue) {
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
