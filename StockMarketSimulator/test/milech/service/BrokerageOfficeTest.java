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
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
	}
	
	@Test
	public void shouldBuy100StocksFor3735() {
		stockMarket.loadNextStock();
		assertEquals(3735, brokerageOffice.buy(100, 0), 0.001);
	}	
	
	@Test
	public void shouldBuy10StocksFor1931() {
		stockMarket.loadNextStocks(2);
		assertEquals(1931, brokerageOffice.buy(10, 1), 0.001);
	}	
	
}
