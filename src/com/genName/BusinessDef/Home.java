package com.genName.BusinessDef;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.BusinessDef.locators.HomePageLocator;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;
import io.qameta.allure.Step;

public class Home extends AppiumActionsHandler {

	Product productDetail;
	DataReader data = new DataReader();
	private HomePageLocator locator;

	// Constructor to decide the platform and load OR respectively.
	public Home() {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new HomePageLocator(strContext);
	}

	/*
	 * Description:open Hamburger menu
	 */
	@Step("Open Hamburger Menu")
	public void openHamMenu(RemoteWebDriver driver) throws Exception {
		if (checkexistenceAndClick(locator.objHamburgerMenu, driver)) {
			Assert.assertTrue( isDisplayed(locator.objShopByCategory, driver) );
		}
	}

	/*
	 * Description: Navigate to ShopBy category from HamburgerMenu
	 */
	@Step("Navigate to 'Shop By Catogory'")
	public void navigateToShopByCatogory(RemoteWebDriver driver) {
		Assert.assertTrue( click(locator.objShopByCategory, driver) );
	}

	/*
	 * Description: Navigate to Category
	 */
	@Step("Navigate to Given Category")
	public void navToCategory(RemoteWebDriver driver, String strCategoryValue) {
		productDetail = data.getProductData(strCategoryValue);
		Assert.assertTrue( checkexistenceAndClick(locator.objCategoryHamburgerMenu, driver, productDetail.getDepartment()) );
	}

	/*
	 * Description: Navigate to SubCategory
	 */
	@Step("Navigate to given SubCategory")
	public void navToSubCategory(RemoteWebDriver driver, String strCategoryValue) {
		productDetail = data.getProductData(strCategoryValue);
		Assert.assertTrue( checkexistenceAndClick(locator.objSubCategoryHamburgerMenu, driver, productDetail.getSubCategory()) );
	}
}
