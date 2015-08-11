package milech.customer;

import java.util.HashMap;
import java.util.Map;

import milech.algorithm.MovingAverageAlg;
import milech.algorithm.RandomAlg;
import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;
import milech.parse.Parser;
import milech.service.BrokerageOffice;

public class CustomerImpl implements Customer {
	private float money;
//	private List<Stock> customerStocks = new ArrayList<Stock>();
	private Map<Integer, Integer> customerStocks = new HashMap<Integer, Integer>();
	private BrokerageOffice brokerageOffice;
	private StockAlgorithm stockAlgorithm;
	
	@Override
	public float getMoney() {
		return money;
	}
	
	public CustomerImpl(BrokerageOffice brokerageOffice) {
		money = 10000;
		stockAlgorithm = chooseAlg(1);
		this.brokerageOffice = brokerageOffice;
	}
	
	@Override
	public void prtCustomerStocks() {
		for(Integer key : customerStocks.keySet()) {
			System.out.println("key: " + key + ", value: " + customerStocks.get(key));
		}
	}
	
	@Override
	public Stock buyToday() {
		int stockToBuyIndex = stockAlgorithm.chooseStockToBuy(brokerageOffice.getStockMarket());
		Stock stockToBuy = brokerageOffice.getStockMarket().getStock(stockToBuyIndex);
		int howManyStocksToBuy = Parser.convMoneyToStock(money, buy(1, stockToBuyIndex));
		float wholeStockCost = buy(howManyStocksToBuy,  stockToBuyIndex);
		System.out.println(wholeStockCost);
		money -= wholeStockCost;
		customerStocks.put(stockToBuyIndex, howManyStocksToBuy);
		return stockToBuy;
	}
	
	@Override
	public float buy(int stockNum, int stockIndex) {
		return brokerageOffice.sell(stockNum, stockIndex);
	}
	
	@Override
	public float sell(int stockNum, int stockIndex) {
		float currStockPrice = brokerageOffice.getPrice(stockIndex); // Index is the Id of the Stock
		return currStockPrice * stockNum;
	}
	
	@Override
	public StockAlgorithm chooseAlg(int algNum) {
		if(algNum == 1)
			return new RandomAlg();
		else 
			return new MovingAverageAlg();
	}
	
	
}
