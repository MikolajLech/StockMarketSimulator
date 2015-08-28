package milech.algorithm;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import milech.entity.Stock;
import milech.entity.Wallet;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;

import org.junit.Before;
import org.junit.Test;

public class AlgorithmTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	BrokerageOffice brokerageOffice;
	MovingAverageAlg algorithm;
	String dataTest = "resources/data.csv";
	float dataAccuracy = 0.01f;
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
		 brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		algorithm = new MovingAverageAlg(2);
	}
	
	@Test
	public void shouldEstimate4DaysMovingAveragePKOBPFalls() {
		stockMarket.moveXDaysForward(4);
		List<Stock> stockHistory = stockMarket.getStockMarketTillToday().getStockHistory("PKOBP");
		assertTrue(algorithm.estimateMovingAverage(stockHistory, 2) < 0);
	}	
	
	@Test
	public void shouldEstimate2DaysMovingAveragePKOBPStable() {
		stockMarket.moveXDaysForward(2);
		List<Stock> stockHistory = stockMarket.getStockMarketTillToday().getStockHistory("PKOBP");
		assertTrue(algorithm.estimateMovingAverage(stockHistory, 2) == 0);
	}	
	
	@Test
	public void shouldEstimate7DaysMovingAverageJSWFalls() {
		stockMarket.moveXDaysForward(7);
		List<Stock> stockHistory = stockMarket.getStockMarketTillToday().getStockHistory("JSW");
		assertTrue(algorithm.estimateMovingAverage(stockHistory, 2) < 0);
	}	
	
	@Test
	public void shouldEstimate4DaysMovingAverageJSWGrows() {
		stockMarket.moveXDaysForward(4);
		List<Stock> stockHistory = stockMarket.getStockMarketTillToday().getStockHistory("JSW");
		assertTrue(algorithm.estimateMovingAverage(stockHistory, 2) > 0);
	}	
	
	@Test
	public void shouldEstimate14DaysMovingAverageJSWFalls() {
		stockMarket.moveXDaysForward(14);
		List<Stock> stockHistory = stockMarket.getStockMarketTillToday().getStockHistory("JSW");
		assertTrue(algorithm.estimateMovingAverage(stockHistory, 2) < 0);
	}	
	
	@Test
	public void shouldChooseStocksToBuy() {
		stockMarket.moveXDaysForward(14);
		Map<String, Integer> stocksToBuyMap = algorithm.chooseStocksToBuy(
				stockMarket.getStockMarketTillToday(), brokerageOffice, new Wallet(10000));
		System.out.println("to buy: " + stocksToBuyMap.toString());
	}	
	
	@Test
	public void shouldChooseStocksToSell() {
		stockMarket.moveXDaysForward(14);
		Map<String, Integer> customerStocks = new TreeMap<String, Integer>();
		customerStocks.put("PKOBP", 100);
		customerStocks.put("TPSA", 50);
		customerStocks.put("JSW", 200);
		System.out.println("customer stocks: " + customerStocks.toString());
		Map<String, Integer> stocksToSellMap = algorithm.chooseStocksToSell(stockMarket.getStockMarketTillToday(), customerStocks);
		System.out.println("to sell: " + stocksToSellMap.toString());
	}	
	
	
}
