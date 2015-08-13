package milech.customer;

import static org.junit.Assert.assertEquals;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	BrokerageOffice brokerageOffice;
	Customer customer;
	String dataTest = "resources/dataTest.csv";
	float dataAccuracy = 0.01f;
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		stockMarket.getNextDay();
		customer = new CustomerImpl(brokerageOffice);
	}
	
	@Test
	public void shouldBuy15StocksFor563_05() {
		assertEquals(563.05, customer.buy(15, "PKOBP"), dataAccuracy);
	}	
	
	@Test
	public void shouldBuy25StocksFor4851_63() {
		assertEquals(4851.63, customer.buy(25, "KGHM"), dataAccuracy);
	}		
	
	@Test
	public void shouldSell10StocksFor373_5() {
		customer.buy(20, "PKOBP");
		assertEquals(373.5, customer.sell(10, "PKOBP"), dataAccuracy);
	}		
	
	@Test
	public void shouldSell30StocksFor157_8() {
		customer.buy(30, "PGNIG");
		assertEquals(157.8, customer.sell(30, "PGNIG"), dataAccuracy);
	}		
	
	@Test
	public void shouldSellAllFor4235_5() {
		customer.buyToday("PKOBP", 10);
		customer.buyToday("KGHM", 20);
		assertEquals(4235.5, customer.sellAll(), dataAccuracy);
	}		
	
	@Test
	public void shouldSellAll2118_8() {
		customer.buyToday("PGNIG", 20);
		customer.buyToday("JSW", 20);
		customer.buyToday("TPSA", 10);
		System.out.println(customer.toString());
		assertEquals(2118.8, customer.sellAll(), dataAccuracy);
	}		

	
}
