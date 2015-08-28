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
	String dataTest = "resources/data.csv";
	float dataAccuracy = 0.01f;
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		stockMarket.moveXDaysForward(1);
	}
	
	@Test
	public void shouldGetStockToBuy37_35() {
		assertEquals(37.35, brokerageOffice.getCurrentBuyPrice("PKOBP"), dataAccuracy);
	}	
	
	@Test
	public void shouldGetStockToBuy5_26() {
		assertEquals(5.26, brokerageOffice.getCurrentBuyPrice("PGNIG"), dataAccuracy);
	}	
	
	@Test
	public void shouldGetStockToSell94_60() {
		assertEquals(94.60, brokerageOffice.getCurrentBuyPrice("JSW"), dataAccuracy);
	}	
	
	@Test
	public void shouldGetStockToSell12_16() {
		assertEquals(12.16, brokerageOffice.getCurrentBuyPrice("TPSA"), dataAccuracy);
	}	
	
	
}
