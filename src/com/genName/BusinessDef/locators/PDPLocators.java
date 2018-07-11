package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;
import com.genName.core.IPageLocator.LocType;

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
		objProductinThePDP 			= LocType.XPATH+":(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']" ;
		txtSalePricePDP 			= LocType.XPATH+":(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@class='sale-price']" ;
		txtOriginalPrice 			= LocType.XPATH+"://div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@id='origprice']" ;
	}

	@Override
	public void tabletLocators() {
		objProductinThePDP = "" ;
		txtSalePricePDP = "" ;
		txtOriginalPrice = "" ;		
	}
	
	

}
