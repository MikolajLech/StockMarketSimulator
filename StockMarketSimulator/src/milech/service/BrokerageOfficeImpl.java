package milech.service;

import milech.entity.Stock;
import milech.repository.StockMarket;

public class BrokerageOfficeImpl implements BrokerageOffice{
	
	private StockMarket stockMarket;
	private float commission;
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
		commission = 0.005f;
	}
	
	private Stock findStockInDay(String stockName) {
        for (Stock stock : stockMarket.getCurrentDay()) {
            if(stock.getName().matches(stockName)) {
            	return stock;
            }
        }
        return null;
	}
	
	public float getCurrentBuyPrice(String stockToBuyName) {
		return findStockInDay(stockToBuyName).getPrice();
	}
	
	public float getCurrentSellPrice(String stockToBuyName) {
		float stockPrice = findStockInDay(stockToBuyName).getPrice();
		return stockPrice + commission * stockPrice;
	}
	
	public StockMarket getStockMarket() {
		return stockMarket;
	}
	
}
