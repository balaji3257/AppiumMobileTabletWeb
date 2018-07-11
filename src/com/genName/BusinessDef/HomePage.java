package com.genName.BusinessDef;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class HomePage extends AppiumActionsHandler {

	Product productDetail ;
	DataReader data = new DataReader();
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
	@Step("Open Hamburger Menu")
	public void openHamMenu(RemoteWebDriver driver) {
		if (checkexistenceAndClick(locator.objHamburgerMenu, driver)) {
			isDisplayed(locator.objShopByCategory, driver);
		}
	}

	/*
	 * Description:
	 */
	@Step("Navigate to 'Shop By Catogory'")
	public void navigateToShopByCatogory(RemoteWebDriver driver) {
		click(locator.objShopByCategory, driver);
	}

	/*
	 * Description:
	 */
	@Step("Navigate to Given Category")
	public void navToCategory(RemoteWebDriver driver, String strCategoryValue) {
		productDetail = data.getProductData(strCategoryValue);		
		checkexistenceAndClick(locator.objCategoryHamburgerMenu, driver, productDetail.getDepartment());
	}

	/*
	 * Description:
	 */
	public void navToSubCategory( RemoteWebDriver driver, String strCategoryValue) {
		productDetail = data.getProductData(strCategoryValue);
		checkexistenceAndClick(locator.objSubCategoryHamburgerMenu, driver, productDetail.getSubCategory());
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
		productDetail = data.getProductData(strProductSize);
		click(locator.dropDownSelectSizeDropDown, driver);
		if (isDisplayed(locator.objSelectSizePopUp_PDP, driver)) {
			checkexistenceAndClick(locator.txtSizeOfTheProduct_PDP, driver, productDetail.getProductSize());
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
