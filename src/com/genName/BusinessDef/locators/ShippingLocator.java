package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class ShippingLocator implements IPageLocator {

	
	public ShippingLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}
	@Override
	public void tabletLocators() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mobileLocators() {
		// TODO Auto-generated method stub

	}

}
