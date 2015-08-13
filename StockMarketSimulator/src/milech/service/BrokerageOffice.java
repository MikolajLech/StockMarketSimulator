package milech.service;

import milech.entity.Stock;
import milech.repository.StockMarket;


public interface BrokerageOffice {

	float buy(int stockNum, String companyName);

	float sell(int stockNum, String companyName);
	
	Stock findStockToBuy(String companyName);
	
	Stock findStockToSell(String companyName);
	
	StockMarket getStockMarket();

}
