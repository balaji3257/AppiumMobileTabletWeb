package com.genName.BusinessDef;

import com.genName.BusinessDef.locators.AccountLocator;
import com.genName.config.DataReader;
import com.genName.config.Utility;
import com.genName.core.AppiumActionsHandler;
import com.genName.datamodels.Product;

public class Account extends AppiumActionsHandler {

	DataReader data = new DataReader();
	private AccountLocator locator;
	Product productDetail;

	// Constructor to decide the platform and load OR respectively.
	public Account() {
		String strContext = null;
		if (Utility.instance().getConfigProperty("Platform").equalsIgnoreCase("MRP")) {
			strContext = "mobile";
		} else {
			strContext = "tablet";
		}
		locator = new AccountLocator(strContext);
	}
}
