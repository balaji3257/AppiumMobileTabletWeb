package com.genName.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.genName.datamodels.Payment;
import com.genName.datamodels.Product;
import com.genName.datamodels.UserDetails;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class DataReader {

	private static final String USERDIR = System.getProperty("user.dir");
	private static final String PRODUCTFILE = USERDIR + "";
	private static final String PAYMENTFILE = USERDIR + "";
	private static final String USERDETAILSFILE = USERDIR + "";

	public enum DataTpe {
		USERDETAILS, PAYMENT, PRODUCT
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Payment getPaymentData(String strReqDataHeader) {
		Payment paymentObject = null;
		String csvFilename = null;
		CSVReader csvReader = null;
		String[] columns = null;
		CsvToBean csv = null;
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		try {
			strategy.setType(Payment.class);
			columns = getPaymentColumns();
			csvFilename = PAYMENTFILE;
			strategy.setColumnMapping(columns);
			csv = new CsvToBean();
			csvReader = new CSVReader(new FileReader(csvFilename));
			List list = csv.parse(strategy, csvReader);
			for (Object object : list) {
				paymentObject = (Payment) object;
				if (paymentObject.getType().equalsIgnoreCase(strReqDataHeader)) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return paymentObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Product getProductData(String strReqDataHeader) {
		Product productObject = null;
		String csvFilename = null;
		CSVReader csvReader = null;
		String[] columns = null;
		CsvToBean csv = null;
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		try {
			strategy.setType(Payment.class);
			columns = getProductColumns();
			csvFilename = PAYMENTFILE;
			strategy.setColumnMapping(columns);
			csv = new CsvToBean();
			csvReader = new CSVReader(new FileReader(csvFilename));
			List list = csv.parse(strategy, csvReader);
			for (Object object : list) {
				productObject = (Product) object;
				if (productObject.getType().equalsIgnoreCase(strReqDataHeader)) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return productObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserDetails getUserDetailsData(String strReqDataHeader) {
		UserDetails productObject = null;
		String csvFilename = null;
		CSVReader csvReader = null;
		String[] columns = null;
		CsvToBean csv = null;
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		try {
			strategy.setType(Payment.class);
			columns = getUserDetailsColumns();
			csvFilename = PAYMENTFILE;
			strategy.setColumnMapping(columns);
			csv = new CsvToBean();
			csvReader = new CSVReader(new FileReader(csvFilename));
			List list = csv.parse(strategy, csvReader);
			for (Object object : list) {
				productObject = (UserDetails) object;
				if (productObject.getType().equalsIgnoreCase(strReqDataHeader)) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return productObject;
	}

	private String[] getUserDetailsColumns() {
		return new String[] { "UserType", "EmailID", "Password", "FirstName", "LastName", "ConfirmEmailID",
				"ConfirmPassword", "MobileNumber", "Address", "City", "State" };
	}

	private String[] getPaymentColumns() {
		return new String[] { "PaymentType", "CardType", "CardNumber", "NameOnCard", "CreditCardID", "ExpirationMonth",
				"ExpirationYear", "PinNo" };
	}

	private String[] getProductColumns() {
		return new String[] { "ProductType", "Department", "Category", "SubCategory", "SKUCode", "ProductSize" };
	}

}
