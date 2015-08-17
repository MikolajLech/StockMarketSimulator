package milech.customer;

import java.util.Map;
import java.util.TreeMap;

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
	
	public int getCustomerStockNum(String companyName) {
		return customerStocks.get(companyName);
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
	
	public void buyWithAlgorithm() {
		String stockToBuyName = stockAlgorithm.chooseStockToBuy(brokerageOffice.getStockMarket());
		int howMuch = Parser.convMoneyToStock(stockAlgorithm.buyForHowMuchMoney((int)money), buy(1, stockToBuyName));
		buyToday(stockToBuyName, howMuch);		
	}
	
	public Stock buyToday(String stockToBuyName, int howMuch) {
		Stock stockToBuy = brokerageOffice.findStockToBuy(stockToBuyName);
		float wholeStockCost = buy(howMuch, stockToBuyName);
		add(stockToBuy.getName(), howMuch);
		money -= wholeStockCost;
		return stockToBuy;
	}
	
	public void add(String companyName, Integer howMuch) {
		if(customerStocks.get(companyName) == null) {
			customerStocks.put(companyName, howMuch);
		}
		else {
			Integer newHowMuch = customerStocks.get(companyName) + howMuch;
			customerStocks.put(companyName, newHowMuch);
		}
	}
	
	public StockAlgorithm chooseAlg(int algNum) {
		if(algNum == 1) {
			return new RandomAlg();
		}
//		return new MovingAverageAlg();
		return null;
	}

	public float sell(int stockNum, String companyName) {
		return brokerageOffice.buy(stockNum, companyName);
	}

	public Stock sellToday(String stockToSellName, int howMuch) {
		Stock stockToSell = brokerageOffice.findStockToSell(stockToSellName);
		if(stockToSell == null) {
			return null;
		}
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
		if(stockToSellName != null) {
			int howMuch = Parser.convMoneyToStock(stockAlgorithm.sellForHowMuchMoney((int)money), sell(1, stockToSellName));
			sellToday(stockToSellName, howMuch);		
		}
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
