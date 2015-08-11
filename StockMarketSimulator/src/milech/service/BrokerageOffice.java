package milech.service;

import milech.repository.StockMarket;

public interface BrokerageOffice {

	float buy(int stockNum, int stockIndex);

	float sell(int stockNum, int stockIndex);

	float getPrice(int stockIndex);

	StockMarket getStockMarket();
	
}
