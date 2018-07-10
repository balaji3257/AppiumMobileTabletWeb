package com.genName.BusinessDef;

public class HomePageLocator implements IPageLocator {

	public String objHamburgerMenu;
	public String objShopByCategory;
	public String objCategoryHamburgerMenu;
	public String objSubCategoryHamburgerMenu;
	public String objProductinThePDP;
	public String txtSalePricePDP;
	public String txtOriginalPrice;
	public String dropDownSelectSizeDropDown;
	public String objSelectSizePopUp_PDP;
	public String txtSizeOfTheProduct_PDP;
	public String txtProductQuantity_PDP;
	public String buttonAddtoCart_PDP;
	public String txtItemAddedToCart ;
	
	public String buttonViewCartApplyPromos;
	public String imgShoppingCartIconWithCount ;
	public String buttonCheckOutShoppingBag ;
	public String buttonGuestCheckOut;

	public HomePageLocator(String strLocatorContext) {
		if (strLocatorContext.equalsIgnoreCase("tablet")) {
			tabletLocators();
		} else {
			mobileLocators();
		}
	}

	@Override
	public void tabletLocators() {
		objShopByCategory  = "" ;
		objCategoryHamburgerMenu = "" ;
		objSubCategoryHamburgerMenu = "" ;
		objProductinThePDP = "" ;
		txtSalePricePDP = "" ;
		txtOriginalPrice = "" ;
		dropDownSelectSizeDropDown = "" ;
		objSelectSizePopUp_PDP = "" ;
		txtSizeOfTheProduct_PDP = "" ;
		txtProductQuantity_PDP = "" ;
		buttonAddtoCart_PDP = "" ;
		txtItemAddedToCart = "";
		buttonViewCartApplyPromos ="";
		imgShoppingCartIconWithCount="";
		buttonCheckOutShoppingBag ="";
		buttonGuestCheckOut ="";
			
	}

	@Override
	public void mobileLocators() {
		objHamburgerMenu 			= LocType.XPATH+"://div[@id='menu-section']";
		objShopByCategory 			= LocType.XPATH+"://li[@class='shop-by-category hb-mid-block']";
		objCategoryHamburgerMenu 	= LocType.XPATH+"://div[@id='mcom-category-menu']/descendant::ul/li[text()='<<<>>>']";
		objSubCategoryHamburgerMenu = LocType.XPATH+"://div[@id='mcom-category-menu']/descendant::ul/li[text()='<<<>>>']";		
		objProductinThePDP 			= LocType.XPATH+":(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']" ;
		txtSalePricePDP 			= LocType.XPATH+":(//div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@class='sale-price']" ;
		txtOriginalPrice 			= LocType.XPATH+"://div[@class='product-name'])[<<<>>>]/ancestor::a[@class='productDtls']/descendant::div[@id='origprice']" ;
		dropDownSelectSizeDropDown 	= LocType.XPATH+"://*[@name='sizeRef1' and text()='Select a size']" ;
		objSelectSizePopUp_PDP 		= LocType.XPATH+"://*[@class='sizelist-title']/ancestor::*[@class='size-enhance-dropdown']" ;
		txtSizeOfTheProduct_PDP 	= LocType.XPATH+"://*[@class='sizelist-title']/ancestor::*[@class='size-enhance-dropdown']/descendant::div[@id='productSizeCln-1' and text()='<<<>>>']" ;
		txtProductQuantity_PDP 		= LocType.XPATH+"://*[@class='quantity-selector']/descendant::input[@id='inpQuantity']" ;
		buttonAddtoCart_PDP 		= LocType.XPATH+"://*[@id='add-to-bag-btn' and text()='Add to Cart' and @class='enabled']" ;
		txtItemAddedToCart			= LocType.XPATH+"://*[text()='Item added to Cart']";
		buttonViewCartApplyPromos	= LocType.XPATH+"://*[text()='VIEW CART & APPLY PROMOS']";
		imgShoppingCartIconWithCount = LocType.XPATH+"://*[@class='mcom-cart-icon cart-enabled']/span[text()='<<<>>>']";
		buttonCheckOutShoppingBag 	= LocType.XPATH+"://a[@class='btn btn-success big btn-block btn-move-to-checkout ng-binding' and text()='Checkout']";
		buttonGuestCheckOut			= LocType.XPATH+"://button[@class='guest-checkout-button' and text()='Guest Checkout']";

	}
}
