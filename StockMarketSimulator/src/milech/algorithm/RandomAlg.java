package milech.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import milech.entity.Stock;
import milech.repository.StockMarket;

public class RandomAlg implements StockAlgorithm {
	private Random generator;
	
	public RandomAlg() {
		generator = new Random();
	}
	
	public Stock chooseStockToBuy(StockMarket stockMarket) {
		int randNum = generator.nextInt(stockMarket.getCurrentDay().size());
		return stockMarket.getCurrentDay().get(randNum);
	}
	
	public String chooseStockToSell(Map<String, Integer> customerStocks) {
		List<String> keys = new ArrayList<String>(customerStocks.keySet());
		int randNum = generator.nextInt(keys.size());
		return keys.get(randNum);
	}
}
