package com.genName.datamodels;


/*
 * This is class used to map the given row of CSV data to java Object.
 */
public class UserDetails {

	private String UserType;
	private String EmailID;
	private String Password;
	private String FirstName;
	private String LastName;
	private String ConfirmEmailID;
	private String ConfirmPassword;
	private String MobileNumber;
	private String Address;
	private String City;
	private String State;

	public String getType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getConfirmEmailID() {
		return ConfirmEmailID;
	}

	public void setConfirmEmailID(String confirmEmailID) {
		ConfirmEmailID = confirmEmailID;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	private String Country;
	private String ZipCode;

}
