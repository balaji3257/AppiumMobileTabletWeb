package com.genName.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;

public class AppiumActionsHandler {

	private static final String strReplaceToken = "-Delimiter-";
	private WebElement objectToBeIdentified = null;
	private static final int DEFAULT_TIMEOUT = 20;

	private SoftAssert softAssert;

	/*
	 * Desc : Check the existence of the given object and click the element .
	 * Author: return : boolean
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver) throws Exception {
		boolean isClicked = false;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
				objectToBeIdentified.click();
				isClicked = true;
			}
		} catch (Exception e) {
			throw new Exception("Non existence of the element : " + strObjectLocator + objectToBeIdentified.toString());
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
		boolean isClicked = false;
		WebElement webElement = null;
		String strPostReplaceXpath = replaceXpathVariables(strObjectLocator, strReplacementValues);
		String[] ObjectAndObjectType = getLocType(strPostReplaceXpath);
		try {
			webElement = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			clickableObject = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			clickableObject = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (clickableObject != null) {
				clickableObject.click();
				isClicked = true;
			}
		} catch (Exception e) {
			presenceOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT, driver).click();
		}
		return isClicked;
	}

	/*
	 * Desc: Send input value to the given field Return : null
	 * 
	 */
	@Step
	protected void sendKeys(String strObjectLocator, String strInputValue, RemoteWebDriver driver) {
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver).sendKeys(strInputValue);
		} catch (Exception e) {
			presenceOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT, driver).sendKeys(strInputValue);
		}
	}

	/*
	 * Desc: Send input value to the given field Return : null
	 * 
	 */
	@Step
	protected void sendKeyswithDynamicLocator(String strObjectLocator, String strInputValue, String strReplacevalue,RemoteWebDriver driver) {
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplacevalue);
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver).sendKeys(strInputValue);
		} catch (Exception e) {
			presenceOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT, driver).sendKeys(strInputValue);
		}
	}

	/*
	 * Desc : check whether given element is selcted or not
	 * Returns: boolean
	 */
	protected boolean isSelected(String strObjectLocator,  RemoteWebDriver driver) throws Exception {
		boolean isSelectedFlag = false;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			isSelectedFlag = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver).isSelected();
		} catch (Exception e) {
			isSelectedFlag = visbilityOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT,driver).isSelected();
		}
		return isSelectedFlag;
	}

	protected boolean isSelectedWithDynamicLocator(String strObjectLocator, String strReplacevalue,RemoteWebDriver driver) throws Exception {

		boolean isSelectedFlag = false;
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplacevalue);
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			isSelectedFlag = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver).isSelected();
		} catch (Exception e) {
			isSelectedFlag = visbilityOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT,driver).isSelected();
		}
		return isSelectedFlag;
	}
	
	private WebElement visbilityOfElement(String strLocator, String strLocatorType, int seconds,RemoteWebDriver driver) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated((by(strLocator, strLocatorType)) ) );
			if(element.isDisplayed() && element==null) {
				
			}
		}catch(Exception e) {
			try{
				// Hande PopUps try block 
			}catch(Exception e1) {
				throw new Exception("");
			}
		}
		return element;
	}

	/*
	 * Desc: wait for the presence of element Return : webElement object
	 */
	private WebElement presenceOfElement(String strLocator, String strLocatorType, int seconds,RemoteWebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by(strLocator, strLocatorType)));
		if (element.isDisplayed()) {
			// future implementation
		}
		return element;
	}

	/*
	 * Desc: returns final By Locator object Return : By object
	 */
	private By by(final String strLocator, final String strLocatorType) {
		By byElement = null;
		switch (strLocatorType.toUpperCase()) {
		case "XPATH":
			byElement = By.xpath(strLocator);
			break;
		case "ID":
			byElement = By.id(strLocator);
			break;
		case "CLASSNAME":
			byElement = By.className(strLocator);
			break;
		}

		return byElement;
	}

	/*
	 * Desc : Return Locator type Author: return : String value
	 */
	@Step
	private String[] getLocType(String strObjectLocator) {
		return strObjectLocator.split(strReplaceToken);
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
				isDisplayedFlag = true;
			}
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return isDisplayedFlag;
	}

	private Boolean waitElementForVisibility(WebElement el, int seconds, RemoteWebDriver driver) {
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
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
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
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

	/*
	 * WebElement findElement(RemoteWebDriver driver) { WebElement element =
	 * driver.findElement(By.id(getSelector())); if (element == null) element =
	 * driver.findElement(By.name(getSelector()); return element;
	 * 
	 * }
	 */
}
