package com.genName.BusinessDef;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.BusinessDef.locators.ShoppingCartLocator;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;

import io.qameta.allure.Step;

public class ShoppingCart extends AppiumActionsHandler {

	Product productDetail;
	DataReader data = new DataReader();
	private ShoppingCartLocator locator;
	
	// Constructor to decide the platform and load OR respectively.
		public ShoppingCart() {
			String strContext = null;
			if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
				strContext = "mobile";
			} else {
				strContext = "tablet";
			}
			locator = new ShoppingCartLocator(strContext);
		}
		
		/*
		 * Description: Navigate to Checkout page from ShoppingCart
		 */
		@Step("clickCheckOutShoppingCart")
		public void clickCheckOutShoppingCart(RemoteWebDriver driver) throws Exception {
			Assert.assertTrue( checkexistenceAndClick(locator.buttonCheckOutShoppingBag, driver) );
		} 

		/*
		 * Description: Navigate to ShippingPage using guest checkout button. 
		 */
		@Step("enterGuestCheckout")
		public void enterGuestCheckout(RemoteWebDriver driver) throws Exception {
			Assert.assertTrue( checkexistenceAndClick(locator.buttonGuestCheckOut, driver) );
		}

}
