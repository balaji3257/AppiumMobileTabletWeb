package com.genName.datamodels;


/*
 * This is class used to map the given row of CSV data to java Object.
 */
public class Product {

	private String ProductType;
	private String Department;
	private String Category;
	private String SubCategory;
	private String SKUCode;
	private String ProductSize;
	
	public String getProductSize() {
		return ProductSize;
	}
	public void setProductSize(String productSize) {
		ProductSize = productSize;
	}
	public String getType() {
		return ProductType;
	}
	public void setProductType(String productType) {
		ProductType = productType;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}
	public String getSKUCode() {
		return SKUCode;
	}
	public void setSKUCode(String sKUCode) {
		SKUCode = sKUCode;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public String getSearchInput() {
		return SearchInput;
	}
	public void setSearchInput(String searchInput) {
		SearchInput = searchInput;
	}
	public String getProdcutColor() {
		return ProdcutColor;
	}
	public void setProdcutColor(String prodcutColor) {
		ProdcutColor = prodcutColor;
	}
	private String ProductCode;
	private String SearchInput;
	private String ProdcutColor;
}
