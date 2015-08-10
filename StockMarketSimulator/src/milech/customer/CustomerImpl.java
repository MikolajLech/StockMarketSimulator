package milech.customer;

import java.util.ArrayList;
import java.util.List;

import milech.entity.Stock;
import milech.service.BrokerageOffice;

public class CustomerImpl implements Customer {
	private float wallet;
	private List<Stock> customerStocks = new ArrayList<Stock>();
	private BrokerageOffice brokerageOffice;
	
	public CustomerImpl(BrokerageOffice brokerageOffice) {
		this.brokerageOffice = brokerageOffice;
	}
	
	@Override
	public float buy(int stockNum, int stockIndex) {
		return brokerageOffice.sell(stockNum, stockIndex);
	}
	@Override
	public float sell(int stockNum, int stockIndex) {
		float currStockPrice = brokerageOffice.getPrice(stockIndex); // Index is the Id of the Stock
		return currStockPrice * stockNum;
	}
	
	
}
