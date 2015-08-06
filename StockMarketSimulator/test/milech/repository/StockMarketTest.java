package milech.repository;

import static org.junit.Assert.*;
import milech.entity.Stock;

import org.junit.Test;

public class StockMarketTest {
	//TODO use spring annotations
	StockMarket stockMarket = new StockMarketImpl();
			
	@Test
	public void shouldLoadNextStock() {
		String dataTest = "resources/dataTest.csv";
		Stock testStock = new Stock("PKOBP", "20130102", "37.35");
		System.out.println("test: " + testStock.toString());
		stockMarket.setDataSource(dataTest);
		stockMarket.loadNextStock();
		System.out.println("load: " + stockMarket.getStock(0));
		assertTrue(testStock.equals(stockMarket.getStock(0)));
	}
	
}
