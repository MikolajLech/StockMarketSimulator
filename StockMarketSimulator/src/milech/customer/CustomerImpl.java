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
	
	public float sell(int stockNum, String companyName) {
		return brokerageOffice.buy(stockNum, companyName);
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
	
	public Stock buyToday(String stockToBuyName, int howMuch) {
//		Stock stockToBuy = stockAlgorithm.chooseStockToBuy(brokerageOffice.getStockMarket());
		Stock stockToBuy = brokerageOffice.findStock(stockToBuyName);
//		int howManyStocksToBuy = Parser.convMoneyToStock(money, buy(1, stockToBuyName)) / 2;
		float wholeStockCost = buy(howMuch, stockToBuyName);
		System.out.println(wholeStockCost);
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

//	public Stock sellToday() {
//		int stockToSellIndex = stockAlgorithm.chooseStockToSell(this);
//		Stock stockToSell = brokerageOffice.getStockMarket().getStock(stockToSellIndex);
//		System.out.println("stsi: " + stockToSellIndex);
//		prtCustomerStocks();
//		
//		Integer howManyStocksToSell = customerStocks.get(stockToSellIndex) / 2;
//		System.out.println("hmsts: " + howManyStocksToSell);
//		float wholeStockSellProfit = 0;
//		if(howManyStocksToSell != null) {
//			wholeStockSellProfit = sell(howManyStocksToSell,  stockToSellIndex);
//		}
//		System.out.println("profit: " + wholeStockSellProfit);
//		money += wholeStockSellProfit;
//		if(customerStocks.get(stockToSellIndex) != null)  {
//			int currStockNum = customerStocks.get(stockToSellIndex) - howManyStocksToSell;
//			if(currStockNum == 0) {
//				customerStocks.remove(stockToSellIndex);
//			}
//			else {
//				customerStocks.put(stockToSellIndex, currStockNum);
//			}
//		}
//		return stockToSell;
//	}

	public float sellAll() {
		float resultMoney = 0;
		for(String stockName : customerStocks.keySet()) {
			resultMoney += sell(customerStocks.get(stockName).intValue(), stockName);
		}
		return resultMoney;
	}
	
	
}
