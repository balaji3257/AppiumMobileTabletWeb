package com.genName.BusinessDef;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.genName.BusinessDef.locators.PMPLocator;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;

import io.qameta.allure.Step;

public class PMP extends AppiumActionsHandler{

	Product productDetail ;
	DataReader data = new DataReader();
	private PMPLocator locator;

	// Constructor to decide the platform and load OR respectively.
	public PMP() {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new PMPLocator(strContext);
	}
	
	
	/*
	 * Description:Navigate to ShoppingCart from Product details page
	 */
	@Step("navToShoppingBag")
	public void navToShoppingBag(RemoteWebDriver driver, String strProductCount) throws Exception {
		checkexistenceAndClick(locator.buttonViewCartApplyPromos, driver);
		isDisplayed(locator.imgShoppingCartIconWithCount, driver, strProductCount);
	}
	
	/*
	 * Description: Add product to cart
	 */
	@Step("addToCart")
	public void addToCart(RemoteWebDriver driver) throws Exception {
		checkexistenceAndClick(locator.buttonAddtoCart_PDP, driver);
		Assert.assertTrue( isDisplayed(locator.txtItemAddedToCart, driver) );
	}
	
	/*
	 * Description: Get Quantity of the product from the ProdDetailsPage
	 */
	@Step("getQtyOFtheProduct")
	public String getQtyOFtheProduct(RemoteWebDriver driver) {
		String currentQuantity = getAttribute(locator.txtProductQuantity_PDP, "value", driver);
		System.out.println("Current Prod Qty = " + currentQuantity);
		return currentQuantity;
	}
	
	/*
	 * Description:Select the given size for the product selected respecively
	 */
	@Step("selectaSizePDP")
	public void selectaSizePDP(RemoteWebDriver driver, String strProductSize) {
		productDetail = data.getProductData(strProductSize);
		click(locator.dropDownSelectSizeDropDown, driver);
		if (isDisplayed(locator.objSelectSizePopUp_PDP, driver)) {
			Assert.assertTrue( checkexistenceAndClick(locator.txtSizeOfTheProduct_PDP, driver, productDetail.getProductSize()) );
			
		}
	}
}
