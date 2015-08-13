package milech.algorithm;

import java.util.ArrayList;
import java.util.Random;

import milech.customer.Customer;
import milech.repository.StockMarket;

public class RandomAlg implements StockAlgorithm {
	private Random generator;
	
	public RandomAlg() {
		generator = new Random();
	}
	@Override
	public int chooseStockToBuy(StockMarket stockMarket) {
		return generator.nextInt(stockMarket.getCurrDaySize());
	}
	@Override
	public int chooseStockToSell(Customer customer) {
		int positon = generator.nextInt(customer.getCustomerStocksSize());
		return (new ArrayList<Integer>(customer.getCustomerStocks().keySet())).get(positon);
	}
}
