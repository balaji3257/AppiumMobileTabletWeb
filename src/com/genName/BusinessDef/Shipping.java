package com.genName.BusinessDef;

import com.genName.BusinessDef.locators.ShippingLocator;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.datamodels.Product;

@SuppressWarnings("unused")
public class Shipping {

	DataReader data = new DataReader();
	
	private ShippingLocator locator;
	Product productDetail ;
	
	public Shipping() {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new ShippingLocator(strContext);
	}
}
