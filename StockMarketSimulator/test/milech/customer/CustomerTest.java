package milech.customer;

import static org.junit.Assert.assertEquals;
import milech.repository.StockMarket;
import milech.repository.StockMarketImpl;
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
		stockMarket = new StockMarketImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		customer = new CustomerImpl(brokerageOffice);
	}
	
	@Test
	public void shouldBuy15StocksFor563_05() {
		stockMarket.loadNextStock();
		assertEquals(563.05, customer.buy(15, 0), dataAccuracy);
	}	
	
	@Test
	public void shouldBuy25StocksFor4851_63() {
		stockMarket.loadNextStocks(2);
		assertEquals(4851.63, customer.buy(25, 1), dataAccuracy);
	}		
	
	@Test
	public void shouldSell10StocksFor373_5() {
		stockMarket.loadNextStock();
		customer.buy(20, 0);
		assertEquals(373.5, customer.sell(10, 0), dataAccuracy);
	}		
	
	@Test
	public void shouldSell30StocksFor157_8() {
		stockMarket.loadNextStocks(3);
		customer.buy(30, 0);
		assertEquals(157.8, customer.sell(30, 2), dataAccuracy);
	}		
}
