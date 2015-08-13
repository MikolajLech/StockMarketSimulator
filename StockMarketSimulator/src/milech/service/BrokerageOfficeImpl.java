package milech.service;

import milech.entity.Stock;
import milech.repository.StockMarket;

public class BrokerageOfficeImpl implements BrokerageOffice{
	
	private StockMarket stockMarket;
	private float commission;
	
	public StockMarket getStockMarket() {
		return stockMarket;
	}
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
		commission = 0.005f;
	}
	
	public float buy(int stockNum, String companyName) {
		return findStockToBuy(companyName).getPrice() * stockNum;
	}
	
	public Stock findStockToBuy(String companyName) {
        for (Stock stock : stockMarket.getCurrentDay()) {
            if(stock.getName().matches(companyName)) {
            	return stock;
            }
        }
        return null;
	}
	
	public Stock findStockToSell(String companyName) {
		for (Stock stock : stockMarket.getCurrentDay()) {
			if(stock.getName().matches(companyName)) {
				return stock;
			}
		}
		return null;
	}
	
	

	public float sell(int stockNum, String companyName) {
		Stock foundStock = findStockToBuy(companyName);
		float wholeCommission = commission * foundStock.getPrice()  * stockNum;
		return foundStock.getPrice() * stockNum + wholeCommission;
	}

}
