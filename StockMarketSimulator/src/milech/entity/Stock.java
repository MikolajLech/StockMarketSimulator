package milech.entity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Stock {
	private String name;
	private Date date;
	private float price;
	public Stock() {
		
	}
	public Stock(String name, String date, String price) {
		this.name = name;
		this.date = parseDate(date);
		this.price = parsePrice(price);
	}
	
	public boolean equals(Stock compStock) {
		return name.equals(compStock.getName()) && date.equals(compStock.getDate()) && price == compStock.getPrice();
	}
	public Date getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	private Date parseDate(String date) {
		Date resultDate = null;
		DateFormat format = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
		try {
			resultDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}


	private float parsePrice(String price) {
		float resultPrice = 0;
		try {
			DecimalFormat format = new DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			resultPrice = format.parse(price).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultPrice;
	}
	public void setDate(String date) {
		this.date = parseDate(date);
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(String price) {
		this.price = parsePrice(price);
	}
	
	public String toString() {
		return name + " " + date.toString() + " " + price;
	}
}
