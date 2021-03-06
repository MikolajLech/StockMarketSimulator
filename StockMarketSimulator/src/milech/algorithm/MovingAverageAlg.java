package milech.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import milech.entity.Stock;
import milech.entity.Wallet;
import milech.repository.StockMarket;
import milech.service.BrokerageOffice;
import milech.stockMarketHelper.StockMarketHelper;

public class MovingAverageAlg implements StockAlgorithm {

	//TODO [milech] Implement long and short term MA' crossovers
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
				currentStockBuyPrice = brokerageOffice.getCurrentSellPrice(stock.getName());
				// TODO [milech] exclude timeFactor influence
				mapOfStocksToBuy.put(stock.getName(), 
						howManyStocksToBuy(movingAverageEstimation, currentStockBuyPrice, wallet));
			}
		}
		return mapOfStocksToBuy;
	}
	
	private int howManyStocksToBuy(float movingAverageEstimation, float currentBuyPrice, Wallet wallet) {
		int stockNumForMoneyFromWallet = StockMarketHelper.howManyStocksForThatMoney(
				wallet.getMoney(), currentBuyPrice);
		return (int)(stockNumForMoneyFromWallet * movingAverageEstimation);
	}

	private int howManyStocksToSell(int customerStockNum, float movingAverageEstimation) {
		return (int)(customerStockNum * (-movingAverageEstimation));
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
					// TODO [milech] exclude timeFactor influence
					mapOfStocksToSell.put(stock.getName(), howManyStocksToSell(customerStockNum, movingAverageEstimation));
				}
			}
		}
		return mapOfStocksToSell;
	}
	
	
	public float estimateMovingAverage(List<Stock> stockHistory, int scope) {
		float previousAverage = 0, currentAverage = 0, growthIndicator = 0, result = 0, timeFactor = 1;
		float timeFactorProgress = 1.01f;
		for(int i = 0; i < stockHistory.size() - scope; i ++) {
			previousAverage = countAverage(getClosedRangeSublist(stockHistory, i, i + scope - 1));
			currentAverage = countAverage(getClosedRangeSublist(stockHistory, i + 1, i + scope));
			growthIndicator = (currentAverage - previousAverage) * timeFactor; // later averages are more important
			result += growthIndicator;
			timeFactor *= timeFactorProgress; // every next day in stock history is 1% more important for the outcome
		}
		return StockMarketHelper.round(result, 2);
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
