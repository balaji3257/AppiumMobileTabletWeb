package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class HomePageLocator implements IPageLocator {

	public String objCategoryHamburgerMenu;
	public String objHamburgerMenu;
	public String objShopByCategory;
	public String objSubCategoryHamburgerMenu;

	public HomePageLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}

	@Override
	public void mobileLocators() {
		objHamburgerMenu 			= LocType.XPATH+"://div[@id='menu-section']";
		objShopByCategory 			= LocType.XPATH+"://li[@class='shop-by-category hb-mid-block']";
		objCategoryHamburgerMenu 	= LocType.XPATH+"://div[@id='mcom-category-menu']/descendant::ul/li[text()='<<<>>>']";
		objSubCategoryHamburgerMenu = LocType.XPATH+"://div[@id='mcom-category-menu']/descendant::ul/li[text()='<<<>>>']";		
				
	}

	@Override
	public void tabletLocators() {
		objShopByCategory  = "" ;
		objCategoryHamburgerMenu = "" ;
		objSubCategoryHamburgerMenu = "" ;
		
			
	}
}
