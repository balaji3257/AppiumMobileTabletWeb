package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class AccountLocator implements IPageLocator {

	public AccountLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}
	@Override
	public void mobileLocators() {
		

	}

	@Override
	public void tabletLocators() {
		

	}

}
