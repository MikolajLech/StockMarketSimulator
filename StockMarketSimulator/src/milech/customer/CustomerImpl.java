package milech.customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import milech.algorithm.MovingAverageAlg;
import milech.algorithm.RandomAlg;
import milech.algorithm.StockAlgorithm;
import milech.entity.Wallet;
import milech.service.BrokerageOffice;
import milech.stockMarketHelper.StockMarketHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customer")
public class CustomerImpl implements Customer {
	private Wallet wallet;
	private Map<String, Integer> customerStocks = new TreeMap<String, Integer>();
	private StockAlgorithm stockAlgorithm;
	private static int ALGORITHM = 2;
	private static int DAYSSCOPE = 28;
	private static int ONEAVERAGESCOPE = 7;
	
	@Autowired
	private BrokerageOffice brokerageOffice;
	
	public CustomerImpl() {
		init();
	}
	
	public CustomerImpl(BrokerageOffice brokerageOffice) {
		init();
		this.brokerageOffice = brokerageOffice;
	}
	
	private void addStockToCustomerStocks(String companyName, Integer howMuch) {
		if(howMuch == 0) {
			return;
		}
		if(customerStocks.get(companyName) == null) {
			customerStocks.put(companyName, howMuch);
		}
		else {
			Integer newHowMuch = customerStocks.get(companyName) + howMuch;
			customerStocks.put(companyName, newHowMuch);
		}
	}
	
	private void buyDifferentStocks(Map<String, Integer> stocksToBuy) {
		//entry 
		for(String stockName : stocksToBuy.keySet()) {
			buySameStock(stockName, stocksToBuy.get(stockName));
		}
	}
	
	public void buySameStock(String stockToBuyName, int howManyStocks) {
		float currentStockPrice = brokerageOffice.getCurrentSellPrice(stockToBuyName);
		float wholeStockCost = currentStockPrice * howManyStocks;
		while(!wallet.ifWalletCanAfford(wholeStockCost) && howManyStocks > 0) {
			howManyStocks--;
			wholeStockCost = currentStockPrice * howManyStocks;
		}
		wallet.takeMoneyFromWallet(wholeStockCost);
		howManyStocks = StockMarketHelper.howManyStocksForThatMoney(wholeStockCost, currentStockPrice);
		addStockToCustomerStocks(stockToBuyName, howManyStocks);
	}
	
	public void buyWithAlgorithm() {
		Map<String, Integer> stocksToBuy = stockAlgorithm.chooseStocksToBuy(
				brokerageOffice.getStockMarket().getStockLastXDaysTillToday(DAYSSCOPE), brokerageOffice, wallet);
		buyDifferentStocks(stocksToBuy);
	}
	
	private StockAlgorithm chooseAlgorithm(int algNum) {
		if(algNum == 1) {
			return new RandomAlg();
		}
		if(algNum == 2) {
			return new MovingAverageAlg(ONEAVERAGESCOPE);
		}
		return null;
	}
	
	public float getMoney() {
		return wallet.getMoney();
	}
	
	private void init() {
		wallet = new Wallet(10000);
		stockAlgorithm = chooseAlgorithm(ALGORITHM);
	}
	
	public float sellAll() {
		float resultMoney = 0;
		List<String> stockNames = new ArrayList<String>(customerStocks.keySet());
		Iterator<String> stockNamesIterator = stockNames.iterator();
		while(stockNamesIterator.hasNext()) {
			String stockName = stockNamesIterator.next();
			resultMoney += sellSameStock(stockName, customerStocks.get(stockName));
		}
		return StockMarketHelper.round(resultMoney, 2);
	}
	
	private void sellManyDifferentStocks(Map<String, Integer> stocksToSell) {
		for(String stockName : stocksToSell.keySet()) {
			sellSameStock(stockName, stocksToSell.get(stockName));
		}		
	}
	
	public float sellSameStock(String stockToSellName, int howManyStocks) {
		if(howManyStocks > customerStocks.get(stockToSellName)) {
			howManyStocks = customerStocks.get(stockToSellName);
		}
		float wholeStockCost = brokerageOffice.getCurrentBuyPrice(stockToSellName) * howManyStocks;
		subtractStockFromCustomerStocks(stockToSellName, howManyStocks);
		wallet.addMoneyToWallet(wholeStockCost);
		return wholeStockCost;
	}
	
	public void sellWithAlgorithm() {
		Map<String, Integer> stocksToSell = stockAlgorithm.chooseStocksToSell(
			brokerageOffice.getStockMarket().getStockLastXDaysTillToday(DAYSSCOPE), customerStocks);
		sellManyDifferentStocks(stocksToSell);
	}
	
	private void subtractStockFromCustomerStocks(String stockName, int howManyStocks) {
		if(customerStocks.get(stockName) == null){
			return;
		}
		Integer howManyStocksHasCustomer = customerStocks.get(stockName);
		if(howManyStocks >= howManyStocksHasCustomer) {
			customerStocks.remove(stockName);
			return;
		}
		Integer newHowMuch = howManyStocksHasCustomer - howManyStocks;
		customerStocks.put(stockName, newHowMuch);
	}
	
	public String toString() {
		String resultString = "";
		if(customerStocks == null) {
			return "Stock is null!";
		}
		if(customerStocks.size() == 0) {
			return "No Stocks to display";
		}
		for(String stockName : customerStocks.keySet()) {
			resultString += stockName;
			if(customerStocks.get(stockName) == null) {
				resultString += " emtpy stock\n";
			}
			else {
				resultString += " " + customerStocks.get(stockName).intValue() + "\n";
			}
		}
		return resultString;
	}
	
}
