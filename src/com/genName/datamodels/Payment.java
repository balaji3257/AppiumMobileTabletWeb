package com.genName.datamodels;

public class Payment {
	
	/*
	 * This is class used to map the given row of CSV data to java Object.
	 */

	private String PaymentType;
	private String CardType;
	private String CardNumber;
	private String NameOnCard;
	private String CreditCardID;
	private String ExpirationMonth;
	private String ExpirationYear;
	private String PinNo;
	public String getType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getNameOnCard() {
		return NameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		NameOnCard = nameOnCard;
	}
	public String getCreditCardID() {
		return CreditCardID;
	}
	public void setCreditCardID(String creditCardID) {
		CreditCardID = creditCardID;
	}
	public String getExpirationMonth() {
		return ExpirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		ExpirationMonth = expirationMonth;
	}
	public String getExpirationYear() {
		return ExpirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		ExpirationYear = expirationYear;
	}
	public String getPinNo() {
		return PinNo;
	}
	public void setPinNo(String pinNo) {
		PinNo = pinNo;
	}
	
}
