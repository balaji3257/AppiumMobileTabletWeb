package com.genName.BusinessDef;

public interface IPageLocator {
	
	public void tabletLocators();
	
	public void mobileLocators();
	
	public enum LocType{
		XPATH,CSS,ID,CLASSNAME,TAGNAME
	}
}
