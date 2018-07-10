package shoppingBag;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.genName.BusinessDef.HomePage;
import com.genName.baseTest.BaseTest;

public class VerifyShoppingBagCount extends BaseTest {

	HomePage home = new HomePage(getWebDriver());

	@Test
	public void VerifyShoppingBagCount() {		

		home.openHamMenu(getWebDriver());
		home.navigateToShopByCatogory(getWebDriver());
		home.navToCategory("Women", getWebDriver());
		home.navToSubCategory("SringToBepassed", getWebDriver());

		home.selectProductFromPdp(getWebDriver(), 1);

		home.getOriginalPrice_Product(getWebDriver());

		home.getSalePrice_Product(getWebDriver());

		home.SelectaSize_PDP("SMALL", getWebDriver());
	}

	@AfterTest
	public void tearDown() {

	}
}
