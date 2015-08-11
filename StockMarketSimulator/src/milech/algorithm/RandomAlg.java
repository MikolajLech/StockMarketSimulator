package milech.algorithm;

import java.util.Random;

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
}
