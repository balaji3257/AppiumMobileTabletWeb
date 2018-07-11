package com.genName.BusinessDef;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.BusinessDef.locators.PDPLocators;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;

import io.qameta.allure.Step;

public class PDP extends AppiumActionsHandler {

	DataReader data = new DataReader();
	private PDPLocators locator;
	Product productDetail;

	// Constructor to decide the platform and load OR respectively.
	public PDP() {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new PDPLocators(strContext);
	}

	/*
	 * Description:Get Original price value from the prodDetailsScreen
	 */
	@Step("getOriginalPriceProduct")
	public String getOriginalPriceProduct(RemoteWebDriver driver, String iproductNumber) {

		String OriginalPrice = getText(locator.txtOriginalPrice, driver, iproductNumber);
		System.out.println("Original Price = " + OriginalPrice);

		return OriginalPrice;
	}

	/*
	 * Description:Get sale price value from the prodDetailsScreen
	 */
	@Step("getSalePriceProduct")
	public String getSalePriceProduct(RemoteWebDriver driver, String iproductNumber) {
		String strSalePrice = getText(locator.txtSalePricePDP, driver, iproductNumber);
		System.out.println(strSalePrice);
		return strSalePrice;
	}

	/*
	 * Description:
	 */
	@Step("selectProductFromPdp")
	public void selectProductFromPdp(RemoteWebDriver driver, String iProductNo) {
		Assert.assertTrue( click(locator.objProductinThePDP, driver, iProductNo) );
	}
	
	
}
