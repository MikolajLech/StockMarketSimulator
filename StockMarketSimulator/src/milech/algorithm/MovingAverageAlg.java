package milech.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import milech.entity.Stock;
import milech.entity.Wallet;
import milech.parser.Parser;
import milech.repository.StockMarket;
import milech.service.BrokerageOffice;

public class MovingAverageAlg implements StockAlgorithm {

	private int oneAverageScope;
	
	public MovingAverageAlg(int oneAverageScope) {
		this.oneAverageScope = oneAverageScope;
	}
	
	public Map<String, Integer> chooseStocksToBuy(StockMarket subStockMarket, BrokerageOffice brokerageOffice, Wallet wallet) {
		Map<String, Integer> mapOfStocksToBuy = new HashMap<String, Integer>();
		if(subStockMarket == null || subStockMarket.getStockMarketDaysNum() == 0) {
			return mapOfStocksToBuy;
		}
		float movingAverageEstimation;
		float currentStockBuyPrice;
		for(Stock stock : subStockMarket.getCurrentDay()) {
			movingAverageEstimation = estimateMovingAverage(
					subStockMarket.getStockHistory(stock.getName()), oneAverageScope);
			if(movingAverageEstimation > 0) {
				currentStockBuyPrice = brokerageOffice.getCurrentBuyPrice(stock.getName());
				// TODO [milech] divide movingAverageEstiomation by how wide sbuStockMarket is * 10
				mapOfStocksToBuy.put(stock.getName(), 
						howManyStocksToBuy(movingAverageEstimation, currentStockBuyPrice, wallet));
			}
		}
		return mapOfStocksToBuy;
	}
	
	private int howManyStocksToBuy(float movingAverageEstimation, float currentBuyPrice, Wallet wallet) {
		int stockNumForMoneyFromWallet = Parser.howManyStocksToBuy(
				wallet.getMoney()/2, currentBuyPrice);
		return (int)(stockNumForMoneyFromWallet * movingAverageEstimation);
	}

	private int howManyStocksToSell(int customerStockNum, float movingAverageEstimation) {
		return (int)(customerStockNum/2 * (-movingAverageEstimation)); // base selling, half of what customer has
	}
	
	public Map<String, Integer> chooseStocksToSell(StockMarket subStockMarket, Map<String, Integer> customerStocks) {
		Map<String, Integer> mapOfStocksToSell = new HashMap<String, Integer>();
		if(customerStocks == null || customerStocks.size() == 0) {
			return mapOfStocksToSell;
		}
		float movingAverageEstimation;
		int customerStockNum;
		for(Stock stock : subStockMarket.getCurrentDay()) {
			if(customerStocks.containsKey(stock.getName())) {
				movingAverageEstimation = estimateMovingAverage(subStockMarket.getStockHistory(stock.getName()), oneAverageScope);
				if(movingAverageEstimation < 0) {
					customerStockNum = customerStocks.get(stock.getName());
					// TODO [milech] divide movingAverageEstiomation by how wide sbuStockMarket is * 10
					mapOfStocksToSell.put(stock.getName(), howManyStocksToSell(customerStockNum, movingAverageEstimation));
				}
			}
		}
		return mapOfStocksToSell;
	}
	
	
	public float estimateMovingAverage(List<Stock> stockHistory, int scope) {
		float previousAverage = 0, currentAverage = 0, growthIndicator = 0, result = 0, timeFactor = 1;
		for(int i = 0; i < stockHistory.size() - scope; i ++) {
			previousAverage = countAverage(getClosedRangeSublist(stockHistory, i, i + scope - 1));
			currentAverage = countAverage(getClosedRangeSublist(stockHistory, i + 1, i + scope));
			growthIndicator = (currentAverage - previousAverage) * timeFactor; // later averages are more important
			result += growthIndicator;
			timeFactor *= 1.1; // every next day in stock history is 10% more important for the outcome
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
