package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class ShoppingCartLocator implements IPageLocator {
	
	
	public String buttonCheckOutShoppingBag ;
	public String buttonGuestCheckOut;
	
	public ShoppingCartLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}

	@Override
	public void mobileLocators() {
		buttonCheckOutShoppingBag 	= LocType.XPATH+"://a[@class='btn btn-success big btn-block btn-move-to-checkout ng-binding' and text()='Checkout']";
		buttonGuestCheckOut			= LocType.XPATH+"://button[@class='guest-checkout-button' and text()='Guest Checkout']";
	}

	@Override
	public void tabletLocators() {
		buttonCheckOutShoppingBag ="";
		buttonGuestCheckOut ="";
	}

}
