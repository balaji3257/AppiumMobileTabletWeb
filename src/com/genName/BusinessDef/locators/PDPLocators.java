package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class PDPLocators implements IPageLocator {

	
	public String objProductinThePDP;
	public String txtOriginalPrice;
	public String txtSalePricePDP;
	
	public PDPLocators(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}

	@Override
	public void mobileLocators() {
		objProductinThePDP 			= LocType.XPATH+DELIMITER+"(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']" ;
		txtSalePricePDP 			= LocType.XPATH+DELIMITER+"(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@class='sale-price']" ;
		txtOriginalPrice 			= LocType.XPATH+DELIMITER+"//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@id='origprice']" ;
	}

	@Override
	public void tabletLocators() {
		objProductinThePDP = "" ;
		txtSalePricePDP = "" ;
		txtOriginalPrice = "" ;		
	}
	
	

}
