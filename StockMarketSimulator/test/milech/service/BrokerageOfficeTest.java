package milech.service;

import static org.junit.Assert.assertEquals;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;

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
		stockMarket = new StockMarketMapImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		stockMarket.getNextDay();
	}
	
	@Test
	public void shouldBuy100StocksFor3735() {
		assertEquals(3735, brokerageOffice.buy(100, "PKOBP"), dataAccuracy);
	}	
	
	@Test
	public void shouldBuy10StocksFor1931() {
		assertEquals(1931, brokerageOffice.buy(10, "KGHM"), dataAccuracy);
	}	
	
	@Test
	public void shouldSell15StocksFor563_05() {
		assertEquals(563.05, brokerageOffice.sell(15, "PKOBP"), dataAccuracy);
	}	
	
	@Test
	public void shouldSell25StocksFor4851_63() {
		assertEquals(4851.63, brokerageOffice.sell(25, "KGHM"), dataAccuracy);
	}	
	
}
