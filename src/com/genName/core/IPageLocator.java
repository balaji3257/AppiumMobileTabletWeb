package com.genName.core;

/*
 * Defines the Basic PageLocator model
 */
public interface IPageLocator {

	public static final String DELIMITER = "mmmm";

	/*
	 * Loads the Tablet based page object locaors
	 */
	public void tabletLocators();

	/*
	 * Loads the Tablet based page object locaors
	 */
	public void mobileLocators();

	public enum LocType {
		XPATH, CSS, ID, CLASSNAME, TAGNAME
	}
}
