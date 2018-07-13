package com.genName.BusinessDef.locators;

import com.genName.core.IPageLocator;

/*
 * This class used to hold the object locators of the respective screen .
 */
public class HomePageLocator implements IPageLocator {

	; 
	public String objCategoryHamburgerMenu;
	public String objDepartmentHamburgerMenu;
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
		objHamburgerMenu 			= LocType.XPATH+DELIMITER+"//div[@id='menu-section']";
		objShopByCategory 			= LocType.XPATH+DELIMITER+"//li[@class='shop-by-category hb-mid-block']";
		objDepartmentHamburgerMenu 	= LocType.XPATH+DELIMITER+"//div[@id='mcom-category-menu']/descendant::li[text()='<<<>>>']";
		objCategoryHamburgerMenu 	= LocType.XPATH+DELIMITER+"//div[@id='mcom-category-menu']/descendant::li[contains(text(),'<<<>>>')]";
		objSubCategoryHamburgerMenu = LocType.XPATH+DELIMITER+"//div[@id='mcom-category-menu']/descendant::li[text()='<<<>>>']";						
	}

	@Override
	public void tabletLocators() {
		objShopByCategory  = "" ;
		objCategoryHamburgerMenu = "" ;
		objSubCategoryHamburgerMenu = "" ;
		
			
	}
}
