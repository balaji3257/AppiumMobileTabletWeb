package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;
import com.genName.core.IPageLocator.LocType;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class PMPLocator implements IPageLocator {

	
	public String buttonAddtoCart_PDP;
	public String buttonViewCartApplyPromos;
	public String dropDownSelectSizeDropDown;
	public String imgShoppingCartIconWithCount ;
	public String objSelectSizePopUp_PDP;
	public String txtItemAddedToCart ;
	public String txtProductQuantity_PDP;
	public String txtSizeOfTheProduct_PDP;
	public PMPLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}
	
	@Override
	public void mobileLocators() {
		buttonViewCartApplyPromos	= LocType.XPATH+"://*[text()='VIEW CART & APPLY PROMOS']";
		imgShoppingCartIconWithCount = LocType.XPATH+"://*[@class='mcom-cart-icon cart-enabled']/span[text()='<<<>>>']";
		buttonAddtoCart_PDP 		= LocType.XPATH+"://*[@id='add-to-bag-btn' and text()='Add to Cart' and @class='enabled']" ;
		txtItemAddedToCart			= LocType.XPATH+"://*[text()='Item added to Cart']";
		txtProductQuantity_PDP 		= LocType.XPATH+"://*[@class='quantity-selector']/descendant::input[@id='inpQuantity']" ;
		dropDownSelectSizeDropDown 	= LocType.XPATH+"://*[@name='sizeRef1' and text()='Select a size']" ;
		objSelectSizePopUp_PDP 		= LocType.XPATH+"://*[@class='sizelist-title']/ancestor::*[@class='size-enhance-dropdown']" ;
		txtSizeOfTheProduct_PDP 	= LocType.XPATH+"://*[@class='sizelist-title']/ancestor::*[@class='size-enhance-dropdown']/descendant::div[@id='productSizeCln-1' and text()='<<<>>>']" ;
	}

	@Override
	public void tabletLocators() {
		buttonViewCartApplyPromos ="";
		imgShoppingCartIconWithCount="";
		buttonAddtoCart_PDP = "" ;
		txtItemAddedToCart = "";
		txtProductQuantity_PDP = "" ;
		dropDownSelectSizeDropDown = "" ;
		objSelectSizePopUp_PDP = "" ;
		txtSizeOfTheProduct_PDP = "" ;
	}

}
