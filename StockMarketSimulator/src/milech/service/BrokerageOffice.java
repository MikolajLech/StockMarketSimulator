package milech.service;

import milech.entity.Stock;
import milech.repository.StockMarket;


public interface BrokerageOffice {

	float buy(int stockNum, String companyName);

	float sell(int stockNum, String companyName);
	
	Stock findStock(String companyName);
	
	StockMarket getStockMarket();

}
