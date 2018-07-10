package com.genName.BusinessDef;

public class HomePageLocator implements IPageLocator {

	public String objHamburgerMenu;
	public String objShopByCategory;
	public String objCategoryHamburgerMenu;
	public String objSubCategoryHamburgerMenu;

	public HomePageLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}

	@Override
	public void tabletLocators() {
		objHamburgerMenu = "";
		objShopByCategory = "";
		objCategoryHamburgerMenu = "";
		objSubCategoryHamburgerMenu = "";
	}

	@Override
	public void mobileLocators() {
		objHamburgerMenu = "";
		objShopByCategory = "";
		objCategoryHamburgerMenu = "";
		objSubCategoryHamburgerMenu = "";
	}
}
