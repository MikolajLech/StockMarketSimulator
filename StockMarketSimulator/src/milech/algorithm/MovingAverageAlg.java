package milech.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import milech.entity.Stock;
import milech.entity.Wallet;
import milech.parser.Parser;
import milech.repository.StockMarket;

public class MovingAverageAlg implements StockAlgorithm {

	public Map<String, Integer> chooseStocksToBuy(StockMarket subStockMarket, Wallet wallet) {
		Map<String, Integer> mapOfStocksToBuy = new HashMap<String, Integer>();
		if(subStockMarket == null || subStockMarket.getStockMarketSize() == 0) {
			return mapOfStocksToBuy;
		}
		float movingAverageEstimation;
//		subStockMarket.moveToDayZero();
		Integer howManyStocks = 0;
////		while(subStockMarket.moveToNextDay()) {
			for(Stock stock : subStockMarket.getCurrentDay()) {
				movingAverageEstimation = estimateMovingAverage(subStockMarket.getStockHistory(stock.getName()), 2);
				if(movingAverageEstimation > 0) {
					// TODO [milech] estimate howManyStocks differently
					howManyStocks = (int)(100 * movingAverageEstimation);
					mapOfStocksToBuy.put(stock.getName(), howManyStocks);
				}
			}
//		}
		return mapOfStocksToBuy;
	}

	public Map<String, Integer> chooseStocksToSell(StockMarket subStockMarket, Map<String, Integer> customerStocks) {
		Map<String, Integer> mapOfStocksToSell = new HashMap<String, Integer>();
		if(customerStocks == null || customerStocks.size() == 0) {
			return mapOfStocksToSell;
		}
		float movingAverageEstimation;
		// TODO [milech] consider moving current day of subStockMarket to the last day
//		subStockMarket.moveToDayZero();
		Integer howManyStocks = 0;
//		while(subStockMarket.moveToNextDay()) {
			for(Stock stock : subStockMarket.getCurrentDay()) {
				if(customerStocks.containsKey(stock.getName())) {
					movingAverageEstimation = estimateMovingAverage(subStockMarket.getStockHistory(stock.getName()), 2);
					if(movingAverageEstimation < 0) {
						// TODO [milech] estimate howManyStocks differently
						howManyStocks = (int)(100 * (-movingAverageEstimation));
						mapOfStocksToSell.put(stock.getName(), howManyStocks);
					}
				}
			}
//		}
		return mapOfStocksToSell;
	}
	
	public float estimateMovingAverage(List<Stock> stockHistory, int scope) {
		float previousAverage = 0, currentAverage = 0, growthIndicator = 0, result = 0, timeFactor = 1;
		for(int i = 0; i < stockHistory.size() - scope; i ++) {
			previousAverage = countAverage(getClosedRangeSublist(stockHistory, i, i + scope - 1));
			currentAverage = countAverage(getClosedRangeSublist(stockHistory, i + 1, i + scope));
			growthIndicator = (currentAverage - previousAverage) * timeFactor; // later averages are more important
			result += growthIndicator;
			timeFactor *= 1.1; // every next day in stock history is 10% more important for the oucome
		}
		return Parser.round(result, 2);
	}
	
	private List<Stock> getClosedRangeSublist(List<Stock> stocks, int beginIndex, int endIndex) {
		List<Stock> resultSubList = new ArrayList<Stock>(stocks.subList(beginIndex, endIndex));
		resultSubList.add(stocks.get(stocks.size()-1));
		return resultSubList;
	}
	
	private float countAverage(List<Stock> stocks) {
		float result = 0;
		for(Stock stock : stocks) {
			result += stock.getPrice();
		}
		return result / stocks.size();
	}

}
