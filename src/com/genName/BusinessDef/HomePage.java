package com.genName.BusinessDef;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.genName.core.AppiumActionsHandler;

public class HomePage extends AppiumActionsHandler{

	public HomePage() {
		
	}

	
	public void openHamMenu(RemoteWebDriver driver) {
		driver.findElement(By.xpath("//div[@id='menu-section']")).click();
	}

	public void navigateToShopByCatogory(RemoteWebDriver driver) {
		driver.findElement(By.xpath("//li[@class='shop-by-category hb-mid-block']")).click();
	}

	public void navToCategory(String strCategory, RemoteWebDriver driver) {
		driver.findElement(By.xpath("//div[@id='mcom-category-menu']/descendant::ul/li[text()='Women']")).click();
	}

	public void navToSubCategory(String strSubCategory, RemoteWebDriver driver) {
		driver.findElement(By.xpath("//div[@id='mcom-category-menu']/descendant::ul/li[text()='Patriotic for Women']"))
				.click();
	}

	public String getOriginalPrice_Product(RemoteWebDriver driver) {
		String OriginalPrice = driver.findElement(By.xpath(
				"(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']/descendant::div[@id='origprice']"))
				.getText();
		System.out.println("Original Price = " + OriginalPrice);

		return OriginalPrice;
	}

	public String getSalePrice_Product(RemoteWebDriver driver) {
		String SalePrice = driver.findElement(By.xpath(
				"(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']/descendant::div[@class='sale-price']"))
				.getText();

		System.out.println(SalePrice);

		return SalePrice;
	}

	public void selectProductFromPdp(RemoteWebDriver driver, int iProductNo) {
		driver.findElement(By.xpath("(//div[@class='product-name'])[1]/ancestor::a[@class='productDtls']")).click();
	}

	public void SelectaSize_PDP(String strSize, RemoteWebDriver driver) {

		driver.findElement(By.xpath("//*[@name='sizeRef1' and text()='Select a size']")).click();

		if (driver.findElement(By.xpath("//*[@class=\"sizelist-title\"]/ancestor::*[@class='size-enhance-dropdown']"))
				.isDisplayed()) {
			driver.findElement(By.xpath(
					"//*[@class=\"sizelist-title\"]/ancestor::*[@class='size-enhance-dropdown']/descendant::div[@id='productSizeCln-1' and text()='SMALL']"))
					.click();
		}
	}

	public int getQtyOFtheProduct(RemoteWebDriver driver) {
		String currentQuantity = driver
				.findElement(By.xpath("//*[@class='quantity-selector']/descendant::input[@id='inpQuantity']"))
				.getAttribute("value");
		System.out.println("Current Prod Qty = " + currentQuantity);
		return Integer.parseInt(currentQuantity);
	}

	public void addToCart(int iCurrentyQty, RemoteWebDriver driver) {
		driver.findElement(By.xpath("//*[@id='add-to-bag-btn' and text()='Add to Cart' and @class='enabled']")).click();
		driver.findElement(By.xpath("//*[text()='Item added to Cart']")).isDisplayed();
	}

	public void navToShoppingBag(RemoteWebDriver driver) {
		driver.findElement(By.xpath("//*[text()='VIEW CART & APPLY PROMOS']")).click();
		driver.findElement(By.xpath("//*[@class='mcom-cart-icon cart-enabled']/span[text()='1']")).isDisplayed();
	}

	public void clickCheckOut_ShoppingCart(RemoteWebDriver driver) {
		driver.findElement(By.xpath(
				"//a[@class='btn btn-success big btn-block btn-move-to-checkout ng-binding' and text()='Checkout']"))
				.click();
	}

	public void enterGuestCheckout(RemoteWebDriver driver) {
		driver.findElement(By.xpath("//button[@class='guest-checkout-button' and text()='Guest Checkout']")).click();
	}

}
