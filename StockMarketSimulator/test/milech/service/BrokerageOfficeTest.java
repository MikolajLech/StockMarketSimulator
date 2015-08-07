package milech.service;

import static org.junit.Assert.assertEquals;
import milech.repository.StockMarket;
import milech.repository.StockMarketImpl;

import org.junit.Before;
import org.junit.Test;

public class BrokerageOfficeTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	BrokerageOffice brokerageOffice;
	String dataTest = "resources/dataTest.csv";
	float dataAccuracy = 0.01f;
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
	}
	
	@Test
	public void shouldBuy100StocksFor3735() {
		stockMarket.loadNextStock();
		assertEquals(3735, brokerageOffice.buy(100, 0), dataAccuracy);
	}	
	
	@Test
	public void shouldBuy10StocksFor1931() {
		stockMarket.loadNextStocks(2);
		assertEquals(1931, brokerageOffice.buy(10, 1), dataAccuracy);
	}	
	
	@Test
	public void shouldSell15StocksFor563_05() {
		stockMarket.loadNextStock();
		assertEquals(563.05, brokerageOffice.sell(15, 0), dataAccuracy);
	}	
	
	@Test
	public void shouldSell25StocksFor4851_63() {
		stockMarket.loadNextStocks(2);
		assertEquals(4851.63, brokerageOffice.sell(25, 1), dataAccuracy);
	}	
	
}
