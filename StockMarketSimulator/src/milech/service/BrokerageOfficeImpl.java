package milech.service;

import milech.repository.StockMarket;

public class BrokerageOfficeImpl implements BrokerageOffice{
	
	private StockMarket stockMarket;
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}
	
	@Override
	public float buy(int stockNum, int stockIndex) {
		return stockMarket.getStock(stockIndex).getPrice() * stockNum;
	}

}
