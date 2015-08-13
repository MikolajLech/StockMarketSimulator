package milech.entity;

import java.util.Date;

import milech.parse.Parser;

public class Stock {
	private String name;
	private Date date;
	private float price;
	
	public Stock() {
		
	}
	
	public Stock(String name, String date, String price) {
		this.name = name;
		this.date = Parser.parseDate(date);
		this.price = Parser.parsePrice(price);
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
	
	public void setDate(String date) {
		this.date = Parser.parseDate(date);
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(String price) {
		this.price = Parser.parsePrice(price);
	}
	
	public String toString() {
		String outStr = "";
		outStr += String.format("%s%10s%10s\n", getDate(), getName(), getPrice());
		return outStr;
	}
}
