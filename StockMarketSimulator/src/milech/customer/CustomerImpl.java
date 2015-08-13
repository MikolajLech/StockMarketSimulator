package milech.customer;

import java.util.Map;
import java.util.TreeMap;

import milech.algorithm.MovingAverageAlg;
import milech.algorithm.RandomAlg;
import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;
import milech.parser.Parser;
import milech.service.BrokerageOffice;

public class CustomerImpl implements Customer {
	private float money;
	private Map<String, Integer> customerStocks = new TreeMap<String, Integer>();
	private BrokerageOffice brokerageOffice;
	private StockAlgorithm stockAlgorithm;
	
	public float getMoney() {
		return money;
	}
	
	public float buy(int stockNum, String companyName) {
		return brokerageOffice.sell(stockNum, companyName);
	}

	public CustomerImpl(BrokerageOffice brokerageOffice) {
		money = 10000;
		stockAlgorithm = chooseAlg(1);
		this.brokerageOffice = brokerageOffice;
	}
	
	public String toString() {
		String resultString = "";
		resultString += "Customer stocks: \n";
		for(String stockName : customerStocks.keySet()) {
			resultString += stockName + " " + customerStocks.get(stockName).intValue() + "\n";
		}
		return resultString;
	}
	
	public void buyWithAlgorithm() {
		String stockToBuyName = stockAlgorithm.chooseStockToBuy(brokerageOffice.getStockMarket()).getName();
		int howMuch = Parser.convMoneyToStock(money, buy(1, stockToBuyName)) / 2;
		buyToday(stockToBuyName, howMuch);		
	}
	
	public Stock buyToday(String stockToBuyName, int howMuch) {
		Stock stockToBuy = brokerageOffice.findStockToBuy(stockToBuyName);
		float wholeStockCost = buy(howMuch, stockToBuyName);
//		System.out.println(wholeStockCost);
		add(stockToBuy, howMuch);
		money -= wholeStockCost;
		return stockToBuy;
	}
	
	public Stock add(Stock stock, Integer howMuch) {
		if(stock == null) {
			return null;
		}
		if(customerStocks.get(stock.getName()) == null) {
			customerStocks.put(stock.getName(), howMuch);
		}
		else {
			Integer newHowMuch = customerStocks.get(stock.getName() + howMuch);
			customerStocks.put(stock.getName(), newHowMuch);
		}
		return stock;
	}
	
	public StockAlgorithm chooseAlg(int algNum) {
		if(algNum == 1) {
			return new RandomAlg();
		}
		return new MovingAverageAlg();
	}

	public float sell(int stockNum, String companyName) {
		return brokerageOffice.buy(stockNum, companyName);
	}

	public Stock sellToday(String stockToSellName, int howMuch) {
		Stock stockToSell = brokerageOffice.findStockToSell(stockToSellName);
		Integer howMuchStockHasCustomer = customerStocks.get(stockToSell.getName());
		if(howMuch > howMuchStockHasCustomer) {
			howMuch = howMuchStockHasCustomer;
		}
		float wholeStockCost = sell(howMuch, stockToSellName);
//		System.out.println(wholeStockCost);
		remove(stockToSell, howMuch);
		money += wholeStockCost;
		return stockToSell;
	}
	
	public void sellWithAlgorithm() {
		String stockToSellName = stockAlgorithm.chooseStockToSell(customerStocks);
		int howMuch = Parser.convMoneyToStock(money, sell(1, stockToSellName)) / 2;
		sellToday(stockToSellName, howMuch);		
	}
	
	public Stock remove(Stock stock, Integer howMuch) {
		Integer howMuchStockHasCustomer = customerStocks.get(stock.getName());
		if(stock == null || customerStocks.get(stock.getName()) == null){
			return null;
		}
		if(howMuch > howMuchStockHasCustomer) {
			howMuch = howMuchStockHasCustomer;
		}
		Integer newHowMuch = howMuchStockHasCustomer - howMuch;
		if(newHowMuch == 0) {
			customerStocks.remove(stock.getName());
		}
		else {
			customerStocks.put(stock.getName(), newHowMuch);
		}
		return stock;
	}
	
	public float sellAll() {
		float resultMoney = 0;
		for(String stockName : customerStocks.keySet()) {
			resultMoney += sell(customerStocks.get(stockName).intValue(), stockName);
		}
		return resultMoney;
	}
	
	
}
