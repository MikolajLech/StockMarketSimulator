package milech.service;

import milech.repository.StockMarket;

public class BrokerageOfficeImpl implements BrokerageOffice{
	
	private StockMarket stockMarket;
	private float commission;
	
	public BrokerageOfficeImpl(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
		commission = 0.005f;
	}
	
	@Override
	public float buy(int stockNum, int stockIndex) {
		return stockMarket.getStock(stockIndex).getPrice() * stockNum;
	}

	@Override
	public float sell(int stockNum, int stockIndex) {
		float wholeCommission = commission *  stockMarket.getStock(stockIndex).getPrice() * stockNum;
		System.out.println(wholeCommission);
		return stockMarket.getStock(stockIndex).getPrice() * stockNum + wholeCommission;
	}

}
