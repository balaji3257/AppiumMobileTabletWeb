package shoppingBag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.genName.BusinessDef.HomePage;
import com.genName.baseTest.BaseTest;

public class VerifyShoppingBagCount extends BaseTest {

	private static final Logger logger = LogManager.getLogger(VerifyShoppingBagCount.class);
	HomePage home = new HomePage(getWebDriver());

	@Test
	public void testVerifyShoppingBagCount() {		
		
		home.openHamMenu(getWebDriver());
		
		home.navigateToShopByCatogory(getWebDriver());
		
		home.navToCategory(getWebDriver(), "Women");
		
		home.navToSubCategory( getWebDriver(), "");
				
		String strOrigPricce = home.getOriginalPriceProduct(getWebDriver() ,"1" );
		
		String strSalePrice = home.getSalePriceProduct(getWebDriver() ,"1");
		
		logger.info("SalePrice = "+strSalePrice);
		
		logger.info("OrignalPrice = "+strOrigPricce);
		home.selectProductFromPdp(getWebDriver(), "1");
		
		home.selectaSizePDP(getWebDriver(), "SMALL");
		
		String strProdQty = home.getQtyOFtheProduct(getWebDriver());
		
		home.addToCart(getWebDriver());
		
		home.navToShoppingBag(getWebDriver(), strProdQty);

	}

}
