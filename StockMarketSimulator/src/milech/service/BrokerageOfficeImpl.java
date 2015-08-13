package milech.service;

import java.util.List;

import milech.entity.Stock;
import milech.repository.StockMarket;

public class BrokerageOfficeImpl implements BrokerageOffice{
	
	private StockMarket stockMarket;
	private float commission;
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
		commission = 0.005f;
	}
	
	public float buy(int stockNum, String companyName) {
		return findStock(stockMarket.getCurrentDay(), companyName).getPrice() * stockNum;
	}
	
	public Stock findStock(List<Stock> list, String companyName) {
        for (Stock stock : list) {
            if(stock.getName().matches(companyName)) {
            	return stock;
            }
        }
        return null;
	}

	public float sell(int stockNum, String companyName) {
		Stock foundStock = findStock(stockMarket.getCurrentDay(), companyName);
		float wholeCommission = commission * foundStock.getPrice()  * stockNum;
		return foundStock.getPrice() * stockNum + wholeCommission;
	}

}
