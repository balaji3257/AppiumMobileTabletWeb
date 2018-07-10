package com.genName.BusinessDef;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;

public class HomePage extends AppiumActionsHandler {

	private HomePageLocator locator;

	// Constructor to decide the platform and load OR respec.
	public HomePage(RemoteWebDriver driver) {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new HomePageLocator(strContext);
	}

	/*
	 * Description:open Hamburger menu using gi
	 */
	public void openHamMenu(RemoteWebDriver driver) {
		if (checkexistenceAndClick(locator.objHamburgerMenu, driver)) {
			isDisplayed(locator.objShopByCategory, driver);
		}
	}

	/*
	 * Description:
	 */
	public void navigateToShopByCatogory(RemoteWebDriver driver) {
		click(locator.objShopByCategory, driver);
	}

	/*
	 * Description:
	 */
	public void navToCategory(RemoteWebDriver driver, String strCategoryValue) {
		checkexistenceAndClick(locator.objCategoryHamburgerMenu, driver, strCategoryValue);
	}

	/*
	 * Description:
	 */
	public void navToSubCategory( RemoteWebDriver driver, String strCategoryValue) {
		checkexistenceAndClick(locator.objSubCategoryHamburgerMenu, driver, strCategoryValue);
	}

	/*
	 * Description:
	 */
	public String getOriginalPriceProduct(RemoteWebDriver driver, String iproductNumber) {

		String OriginalPrice = getText(locator.txtOriginalPrice, driver, iproductNumber);
		System.out.println("Original Price = " + OriginalPrice);

		return OriginalPrice;
	}

	/*
	 * Description:
	 */
	public String getSalePriceProduct(RemoteWebDriver driver, String iproductNumber) {
		String strSalePrice = getText(locator.txtSalePricePDP, driver, iproductNumber);
		System.out.println(strSalePrice);
		return strSalePrice;
	}

	/*
	 * Description:
	 */
	public void selectProductFromPdp(RemoteWebDriver driver, String iProductNo) {
		click(locator.objProductinThePDP, driver, iProductNo);
		isDisplayed(locator.dropDownSelectSizeDropDown, driver);
	}

	/*
	 * Description:
	 */
	public void selectaSizePDP(RemoteWebDriver driver, String strProductSize) {
		click(locator.dropDownSelectSizeDropDown, driver);
		if (isDisplayed(locator.objSelectSizePopUp_PDP, driver)) {
			checkexistenceAndClick(locator.txtSizeOfTheProduct_PDP, driver, strProductSize);
		}
	}

	/*
	 * Description:
	 */
	public String getQtyOFtheProduct(RemoteWebDriver driver) {
		String currentQuantity = getAttribute(locator.txtProductQuantity_PDP, "value", driver);
		System.out.println("Current Prod Qty = " + currentQuantity);
		return currentQuantity;
	}

	/*
	 * Description:
	 */
	public void addToCart(RemoteWebDriver driver) {
		checkexistenceAndClick(locator.buttonAddtoCart_PDP, driver);
		isDisplayed(locator.txtItemAddedToCart, driver);
	}

	
	/*
	 * Description:
	 */
	public void navToShoppingBag(RemoteWebDriver driver , String strProductCount) {
		checkexistenceAndClick(locator.buttonViewCartApplyPromos, driver);
		isDisplayed(locator.imgShoppingCartIconWithCount, driver, strProductCount);
	}

	
	/*
	 * Description:
	 */
	public void clickCheckOutShoppingCart(RemoteWebDriver driver) {		
		checkexistenceAndClick(locator.buttonCheckOutShoppingBag, driver);
	}

	/*
	 * Description:
	 */
	public void enterGuestCheckout(RemoteWebDriver driver) {
		checkexistenceAndClick(locator.buttonGuestCheckOut, driver);
	}

}
