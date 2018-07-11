package shoppingBag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.genName.BusinessDef.HomePage;
import com.genName.baseTest.BaseTest;
import com.genName.datamodels.Payment;
import com.genName.datamodels.Product;
import com.genName.datamodels.UserDetails;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("REGRESSION")
public class VerifyShoppingBagCount extends BaseTest {

	private static final Logger logger = LogManager.getLogger(VerifyShoppingBagCount.class);
	Payment paymentData = new Payment();
	Product productData = new Product();
	UserDetails userDetaildata = new UserDetails();

	public static final String PRODUCT_ORDER 	= "2";
	public static final String PRODUCT_DETAIL 	= "ShopByCategory";
	public static final String PAYMENTTYPE 		= "";
	public static final String USERDETAILSTYPE 	= "";
	HomePage home = new HomePage(getWebDriver());

	@Feature("ShoppingBag")
	@Description("Verify shoppingBag Count")
	@Test
	public void testVerifyShoppingBagCount() {

		home.openHamMenu(getWebDriver());

		home.navigateToShopByCatogory(getWebDriver());

		home.navToCategory(getWebDriver(), PRODUCT_DETAIL);

		home.navToSubCategory(getWebDriver(), PRODUCT_DETAIL);

		String strOrigPricce = home.getOriginalPriceProduct(getWebDriver(), PRODUCT_ORDER);

		String strSalePrice = home.getSalePriceProduct(getWebDriver(), PRODUCT_ORDER);

		logger.info("SalePrice = " + strSalePrice);

		logger.info("OrignalPrice = " + strOrigPricce);
		home.selectProductFromPdp(getWebDriver(), PRODUCT_ORDER);

		home.selectaSizePDP(getWebDriver(), PRODUCT_DETAIL);

		String strProdQty = home.getQtyOFtheProduct(getWebDriver());

		home.addToCart(getWebDriver());

		home.navToShoppingBag(getWebDriver(), strProdQty);

	}

}
