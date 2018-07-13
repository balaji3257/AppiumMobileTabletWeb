package com.genName.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.genName.exceptionHandler.OwnException;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


public class AppiumActionsHandler {

	private static final Logger logger = LogManager.getLogger(AppiumActionsHandler.class);
	private static final String REPLACE_TOKEN = "<<<>>>";
	private WebElement objectToBeIdentified = null;
	private static final int DEFAULT_TIMEOUT = 20;
	
	
	private static final String LOC_XPATH = "XPATH";
	private static final String LOC_ID = "ID";
	private static final String LOC_CSS = "CSS";
	private static final String LOC_CLASSNAME = "CLASSNAME";
	private static final String LOC_NAME ="NAME";

	public enum LocatorType {
		XPATH, ID, CSS, CLASSNAME
	}

	private SoftAssert softAssert;

	
	/*
	 * Desc: TakeScreenShot to the allure report
	 * return : byte arrray
	 */
	@Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG (RemoteWebDriver  driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
	
	
	//Text attachments for Allure
    @Attachment(value = "Error Message")
    public static String saveTextLog (String message) {
        return message;
    }
	/*
	 * Desc : Check the existence of the given object and click the element .
	 * Author: return : boolean
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver) throws OwnException {
		logger.info("***checkexistenceAndClick ***");
		boolean isClicked = false;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, DEFAULT_TIMEOUT, driver)) {
				objectToBeIdentified.click();
				isClicked = true;
			}
		} catch (Exception e) {
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			throw new OwnException("Non existence of the element : " + strObjectLocator + objectToBeIdentified.toString());
		}
		return isClicked;
	}

	/*
	 * Desc : Check the existence of the given object and click the element based on
	 * the given dynamic xpath value. Author: return : boolean
	 */
	@Step
	protected boolean checkexistenceAndClick(String strObjectLocator, RemoteWebDriver driver,String strReplacementValues) {
		logger.info("***checkexistenceAndClickwithDynamicLocator***");
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
			saveScreenshotPNG(driver);
			saveTextLog(e.getMessage());
			logger.error("Unable to get checkelement", e);
		}
		return isClicked;
	}

	/*
	 * Desc : Check the element based on the given xpath value. Author: return :
	 * boolean
	 */
	@Step
	protected boolean click(String strObjectLocator, RemoteWebDriver driver) {
		logger.info("***click***");
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
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			logger.error("Unable to get text value ", e);
		}
		return isClicked;
	}

	/*
	 * Desc : Check the element based on the given dynamic xpath value. Author:
	 * return : boolean
	 */
	@Step
	protected boolean click(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {
		logger.info("***click***");
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
		logger.info("***sendKeys***");
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
		logger.info("***sendKeyswithDynamicLocator***");
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
	
	protected boolean isSelected(String strObjectLocator,  RemoteWebDriver driver) throws OwnException {
		logger.info("***isSelected***");
		boolean isSelectedFlag = false;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			isSelectedFlag = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver).isSelected();
		} catch (Exception e) {
			isSelectedFlag = visbilityOfElement(ObjectAndObjectType[1], ObjectAndObjectType[0], DEFAULT_TIMEOUT,driver).isSelected();
		}
		return isSelectedFlag;
	}

	protected boolean isSelectedWithDynamicLocator(String strObjectLocator, String strReplacevalue,RemoteWebDriver driver) throws OwnException {
		logger.info("***isSelectedWithDynamicLocator***");
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
	
	private WebElement visbilityOfElement(String strLocator, String strLocatorType, int seconds,RemoteWebDriver driver) throws OwnException {
		logger.info("***visbilityOfElement***");
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated((by(strLocator, strLocatorType)) ) );
		}catch(Exception e) {
			try{
				// Hande PopUps try block 
			}catch(Exception e1) {
				throw new OwnException("Failed in checking the visibility of element");
			}
		}
		return element;
	}

	/*
	 * Desc: wait for the presence of element Return : webElement object
	 */
	private WebElement presenceOfElement(String strLocator, String strLocatorType, int seconds,RemoteWebDriver driver) {
		logger.info("***presenceOfElememt***");
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

		switch (strLocatorType.toUpperCase()) {
		case LOC_XPATH:
			return By.xpath(strLocator);
		case LOC_ID:
			return By.id(strLocator);
		case LOC_CLASSNAME:
			return By.className(strLocator);
		default:
			return By.xpath(strLocator);
		}
	}

	/*
	 * Desc : Return Locator type Author: return : String value
	 */
	@Step
	private String[] getLocType(String strObjectLocator) {
		return strObjectLocator.split(IPageLocator.DELIMITER);
	}

	/*
	 * Desc : Returns softAssert Object. Author: return : soft assert object
	 */
	@Step
	protected SoftAssert getSoftAssert() {
		logger.info("***Get Soft Assert***");
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
		logger.info("***GetText With Dynamic locator***");
		String strFetchValue = null;

		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (objectToBeIdentified != null) {
				strFetchValue = objectToBeIdentified.getText();
			}
		} catch (Exception e) {
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			logger.error("Unable to get text value ", e);
		}

		return strFetchValue;
	}

	/*
	 * Desc: Check the existence of the given element Author: Return: boolean
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver) {
		logger.info("***isDisplayed***");
		boolean isDisplayedFlag = false;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
				isDisplayedFlag = true;
			}
		} catch (Exception e) {
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			logger.error("Unable to get element value ", e);
		}
		return isDisplayedFlag;
	}

	private Boolean waitElementForVisibility(WebElement el, int seconds, RemoteWebDriver driver) throws OwnException {
		logger.info("***waitElementForVisibility***");
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOf(el));
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new OwnException(e.toString());
		}
	}

	/*
	 * Desc: Check the existence of the given element Author: Return: boolean
	 */
	@Step
	protected boolean isDisplayed(String strObjectLocator, RemoteWebDriver driver, String strReplaceValue) {
		logger.info("***Is Displayed With Dynamic locator***");
		boolean isDisplayedFlag = false;
		strObjectLocator = replaceXpathVariables(strObjectLocator, strReplaceValue);
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (waitElementForVisibility(objectToBeIdentified, 10, driver)) {
				isDisplayedFlag = true;
			}
		} catch (Exception e) {
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			logger.error("Unable to get element value ", e);
		}
		return isDisplayedFlag;
	}

	/*
	 * Desc: Return the value of the given attribute dynamically Author: Return:
	 * String
	 */
	@Step
	protected String getAttribute(String strObjectLocator, String strAttributeValue, RemoteWebDriver driver) {
		logger.info("***Get Attribute***");
		String strFetchAttributeValue = null;
		String[] ObjectAndObjectType = getLocType(strObjectLocator);
		try {
			objectToBeIdentified = returnElement(ObjectAndObjectType[1], ObjectAndObjectType[0], driver);
			if (objectToBeIdentified != null) {
				strFetchAttributeValue = objectToBeIdentified.getAttribute(strAttributeValue);
			}
		} catch (Exception e) {
			saveScreenshotPNG(driver);
			saveTextLog(e.toString());
			logger.error("Unable to get attribute value ", e);
		}
		return strFetchAttributeValue;
	}

	/*
	 * Desc: Replace the given String value in the Object string Author: Return:
	 * String
	 */
	@Step
	private String replaceAllXpathVariable(String strObjectLocator, String[] strReplaceValue) {
		logger.info("***ReplaceXpathWithDynamicVariables***");
		String strPostReplacement = null;
		for (String strSinglReplaceValue : strReplaceValue) {
			strPostReplacement = replaceXpathVariables(strObjectLocator, strSinglReplaceValue);
		}
		return strPostReplacement;
	}

	/*
	 * Desc: Replace the given String value in the Object string Author: 
	 * Return : String
	 * 
	 */
	@Step
	private String replaceXpathVariables(String strObjectLocator, String strReplaceValue) {
		logger.info("***ReplaceXpathWithDynamicVariables***");
		String strPostReplacement = strObjectLocator.replace(REPLACE_TOKEN, strReplaceValue);
		 return strPostReplacement;		
	}

	/*
	 * Desc: Returns the element of the given locator. Author: Return: WebElement
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver) throws OwnException  {
		logger.info("***ReturnELementMethod***");
		try {
		switch (strLocaType.toUpperCase()) {
			case LOC_XPATH:
				return driver.findElement(By.xpath(objectName));
			case LOC_CSS:
				return driver.findElement(By.cssSelector(objectName));
			case LOC_ID:
				return driver.findElement(By.id(objectName));
			case LOC_NAME:
				return driver.findElement(By.name(objectName));
			case LOC_CLASSNAME:
				return driver.findElement(By.className(objectName));
			default:
				return driver.findElement(By.xpath(objectName));
			}
		} catch (Exception e) {
				throw new OwnException("Unable to identify element using [---"+objectName+"---]");
		}
	}

	/*
	 * Desc: Returns the element of the given locator dynamically. Author: Return:
	 * WebElement
	 */
	@Step
	protected WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver,String replacementValue) {
		if (replacementValue == null)
			return returnElement(objectName, strLocaType, driver, new String[0]);
		else
			return returnElement(objectName, strLocaType, driver, new String[] { replacementValue });
	}

	/*
	 * Desc: Returns the element of the given locator dynamically. Author: 
	 * Return: WebElement
	 * 
	 */
	@Step
	private WebElement returnElement(String objectName, String strLocaType, RemoteWebDriver driver,String[] replacementValue) {
		logger.info("***ReturnELementMethodWithDynamicXLocators***");
		String lookupValue = null;
		objectName = objectName.split(":")[1].trim();
		lookupValue = replaceAllXpathVariable(objectName, replacementValue);
		switch (strLocaType.toUpperCase()) {
		case LOC_XPATH:
			return driver.findElement(By.xpath(lookupValue));
		case LOC_CSS:
			return driver.findElement(By.cssSelector(lookupValue));
		case LOC_ID:
			return driver.findElement(By.id(lookupValue));
		case LOC_NAME:
			return driver.findElement(By.name(lookupValue));
		default:
			return driver.findElement(By.xpath(lookupValue));
		}
	}
}
