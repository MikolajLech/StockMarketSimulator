package milech.algorithm;

import java.util.Random;

import milech.entity.Stock;
import milech.repository.StockMarket;

public class RandomAlg implements StockAlgorithm {
	private Random generator;
	
	public RandomAlg() {
		generator = new Random();
	}
	@Override
	public Stock chooseStockToBuy(StockMarket stockMarket) {
		int randNum = generator.nextInt(stockMarket.getCurrentDay().size());
		return stockMarket.getCurrentDay().get(randNum);
	}
	
//	@Override
//	public int chooseStockToSell(Customer customer) {
//		int positon = generator.nextInt(customer.getCustomerStocksSize());
//		return (new ArrayList<Integer>(customer.getCustomerStocks().keySet())).get(positon);
//	}
}
