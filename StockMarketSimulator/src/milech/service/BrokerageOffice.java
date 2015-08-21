package milech.service;

import milech.repository.StockMarket;


public interface BrokerageOffice {
	StockMarket getStockMarket();
	float getCurrentBuyPrice(String stockToBuyName);
	float getCurrentSellPrice(String stockToBuyName);
}
