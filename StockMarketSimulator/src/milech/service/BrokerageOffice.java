package milech.service;

import milech.repository.StockMarket;


public interface BrokerageOffice {

//	float buy(int stockNum, String companyName);
//
//	float sell(int stockNum, String companyName);
	
	StockMarket getStockMarket();
	
	float getCurrentBuyPrice(String stockToBuyName);
	public float getCurrentSellPrice(String stockToBuyName);

}
