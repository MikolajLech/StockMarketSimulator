package milech.repository;

import static org.junit.Assert.assertTrue;
import milech.entity.Stock;

import org.junit.Before;
import org.junit.Test;

public class StockMarketTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	String dataTest = "resources/dataTest.csv";
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketImpl(dataTest);
	}
	
	@Test
	public void shouldLoadNextStock() {
		Stock testStock = new Stock("PKOBP", "20130102", "37.35");
		stockMarket.loadNextStock();
		assertTrue(testStock.equals(stockMarket.getStock(0)));
	}
	
	@Test
	public void shouldLoadNextDay() {
		stockMarket.loadNextDay();
		StockMarket testMarket = new StockMarketImpl();
		testMarket.add(new Stock("PKOBP", "20130102", "37.35"));
		testMarket.add(new Stock("KGHM", "20130102", "193.1"));
		testMarket.add(new Stock("PGNIG", "20130102", "5.26"));
		testMarket.add(new Stock("JSW", "20130102", "94.6"));
		testMarket.add(new Stock("TPSA", "20130102", "12.16"));
		assertTrue(testMarket.equals(stockMarket));
	}
	
	@Test
	public void shouldLoad2NextDays() {
		stockMarket.loadNextDay();
		stockMarket.loadNextDay();
		stockMarket.prtStockMarket();
		StockMarket testMarket = new StockMarketImpl();
		testMarket.add(new Stock("PKOBP", "20130102", "37.35"));
		testMarket.add(new Stock("KGHM", "20130102", "193.1"));
		testMarket.add(new Stock("PGNIG", "20130102", "5.26"));
		testMarket.add(new Stock("JSW", "20130102", "94.6"));
		testMarket.add(new Stock("TPSA", "20130102", "12.16"));
		testMarket.add(new Stock("PKOBP", "20130103", "37.35"));
		testMarket.add(new Stock("KGHM", "20130103", "193.5"));
		testMarket.add(new Stock("PGNIG", "20130103", "5.25"));
		testMarket.add(new Stock("JSW", "20130103", "96.4"));
		testMarket.add(new Stock("TPSA", "20130103", "12.13"));
		assertTrue(testMarket.equals(stockMarket));
	}
	
}
