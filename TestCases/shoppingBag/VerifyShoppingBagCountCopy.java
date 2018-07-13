package shoppingBag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.genName.BusinessDef.Home;
import com.genName.BusinessDef.PDP;
import com.genName.BusinessDef.PMP;
import com.genName.BusinessDef.ShoppingCart;
import com.genName.baseTest.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("REGRESSION")
public class VerifyShoppingBagCountCopy extends BaseTest {

	// logger - to log executuion details
	private static final Logger logger = LogManager.getLogger(VerifyShoppingBagCountCopy.class);

	public static final String PRODUCT_ORDER = "2";
	public static final String PRODUCT_DETAIL = "ShopByCategory";
	public static final String PAYMENTTYPE = "";
	public static final String USERDETAILSTYPE = "";
	Home home = new Home();
	PDP pdp = new PDP();
	PMP pmp = new PMP();
	ShoppingCart cart = new ShoppingCart();

	// Test method - implementation using TestNG unit testing framework
	@Feature("ShoppingBag")
	@Description("Verify shoppingBag Count")
	@Test(groups = { "ExampleGroupName" })
	public void testVerifyShoppingBagCount() throws Exception {

		home.openHamMenu(getWebDriver());

		home.navigateToShopByCatogory(getWebDriver());
		
		/*home.navToDepartment(getWebDriver(), PRODUCT_DETAIL);

		home.navToCategory(getWebDriver(), PRODUCT_DETAIL);

		home.navToSubCategory(getWebDriver(), PRODUCT_DETAIL);

		String strOrigPricce = pdp.getOriginalPriceProduct(getWebDriver(), PRODUCT_ORDER);

		String strSalePrice = pdp.getSalePriceProduct(getWebDriver(), PRODUCT_ORDER);

		logger.info("SalePrice = " + strSalePrice);

		logger.info("OrignalPrice = " + strOrigPricce);

		pdp.selectProductFromPdp(getWebDriver(), PRODUCT_ORDER);

		pmp.selectaSizePDP(getWebDriver(), PRODUCT_DETAIL);

		String strProdQty = pmp.getQtyOFtheProduct(getWebDriver());

		pmp.addToCart(getWebDriver());

		pmp.navToShoppingBag(getWebDriver(), strProdQty);

		cart.clickCheckOutShoppingCart(getWebDriver());

		cart.enterGuestCheckout(getWebDriver());*/
	}

}
