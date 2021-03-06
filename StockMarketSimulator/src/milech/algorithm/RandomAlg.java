package milech.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import milech.entity.Stock;
import milech.entity.Wallet;
import milech.repository.StockMarket;
import milech.service.BrokerageOffice;

public class RandomAlg implements StockAlgorithm {
	private Random generator;
	
	public RandomAlg() {
		generator = new Random();
	}
	
	public Map<String, Integer> chooseStocksToBuy(StockMarket subStockMarket, BrokerageOffice brokerageOffice, Wallet wallet) {
		Map<String, Integer> mapOfStocksToBuy = new HashMap<String, Integer>();
		if(subStockMarket == null || subStockMarket.getStockMarketDaysNum() == 0) {
			return mapOfStocksToBuy;
		}
		int daySize = subStockMarket.getCurrentDay().size();
		int stocksToBuyNum =  generator.nextInt(daySize);
		Integer howManyStocks = 0;
		Stock randomStock;
		for(int i = 0; i < stocksToBuyNum; i++) {
			do {
				randomStock = subStockMarket.getCurrentDay().get(generator.nextInt(daySize));
			} while(mapOfStocksToBuy.containsKey(randomStock.getName()));
			while((howManyStocks = generator.nextInt(100)) == 0);
			mapOfStocksToBuy.put(randomStock.getName(), howManyStocks);
		}
		return mapOfStocksToBuy;
	}
	
	public Map<String, Integer> chooseStocksToSell(StockMarket subStockMarket, Map<String, Integer> customerStocks) {
		Map<String, Integer> mapOfStocksToSell = new HashMap<String, Integer>();
		List<String> keys = new ArrayList<String>(customerStocks.keySet());
		if(keys == null || keys.size() == 0 ) {
			return mapOfStocksToSell;
		}
		String randomStockName = keys.get(generator.nextInt(keys.size()));
		int howManyStocks = generator.nextInt(100);
		mapOfStocksToSell.put(randomStockName, howManyStocks);
		return mapOfStocksToSell;
	}
	
//	private float randomFloatInScope(float min, float max) {
//		return generator.nextFloat() * (min - max) + min;
//	}
}
