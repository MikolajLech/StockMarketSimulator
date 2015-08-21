package milech.service;

import milech.entity.Stock;
import milech.repository.StockMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("brokerageOffice")
public class BrokerageOfficeImpl implements BrokerageOffice{
	
	@Autowired
	private StockMarket stockMarket;
	
	private float commission;
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
		commission = 0.005f;
	}
	
	public BrokerageOfficeImpl() {
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
