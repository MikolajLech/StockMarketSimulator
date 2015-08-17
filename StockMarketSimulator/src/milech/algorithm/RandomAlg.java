package milech.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import milech.repository.StockMarket;

public class RandomAlg implements StockAlgorithm {
	private Random generator;
	
	public RandomAlg() {
		generator = new Random();
	}
	
	public String chooseStockToBuy(StockMarket stockMarket) {
		int randNum = generator.nextInt(stockMarket.getCurrentDay().size());
		return stockMarket.getCurrentDay().get(randNum).getName();
	}
	
	public String chooseStockToSell(Map<String, Integer> customerStocks) {
		List<String> keys = new ArrayList<String>(customerStocks.keySet());
		if(keys.size() == 0) {
			return null;
		}
		int randNum = generator.nextInt(keys.size());
		return keys.get(randNum);
	}
	
	public int buyForHowMuchMoney(int money) {
		return generator.nextInt(money);
	}

	public int sellForHowMuchMoney(int money) {
		return generator.nextInt(money);
	}

}
