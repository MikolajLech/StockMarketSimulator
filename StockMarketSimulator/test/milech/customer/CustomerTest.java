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
		assertEquals(563.05, customer.getCurrentPriceAtBroker(15, "PKOBP"), dataAccuracy);
	}	
	
	@Test
	public void shouldBuy25StocksFor4851_63() {
		assertEquals(4851.63, customer.getCurrentPriceAtBroker(25, "KGHM"), dataAccuracy);
	}		
	
	@Test
	public void shouldSell10StocksFor373_5() {
		customer.getCurrentPriceAtBroker(20, "PKOBP");
		assertEquals(373.5, customer.sell(10, "PKOBP"), dataAccuracy);
	}		
	
	@Test
	public void shouldSell30StocksFor157_8() {
		customer.getCurrentPriceAtBroker(30, "PGNIG");
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
		assertEquals(2118.8, customer.sellAll(), dataAccuracy);
	}		
	
	@Test
	public void shouldBuyTodayFor52_6() {
		customer.buyToday("PGNIG", 10);
		assertEquals(10000 - 52.863, customer.getMoney(), dataAccuracy);
	}		
	
	@Test
	public void shouldBuyTodayFor3881_31() {
		customer.buyToday("KGHM", 20);
		assertEquals(10000 - 3881.31, customer.getMoney(), dataAccuracy);
	}		
	
	@Test
	public void shouldSellTodayWithOutcome8049_49() {
		customer.buyToday("KGHM", 20);
		customer.sellToday("KGHM", 10);
		assertEquals(8049.69, customer.getMoney(), dataAccuracy);
	}		
	
	@Test
	public void shouldntSellMoreThanHasCustomer9980_69() {
		customer.buyToday("KGHM", 20);
		customer.sellToday("KGHM", 30);
		assertEquals(9980.69, customer.getMoney(), dataAccuracy);
	}		
	
	@Test
	public void shouldAdd20Stocks() {
		customer.buyToday("PKOBP", 10);
		customer.buyToday("PKOBP", 10);
		assertEquals(20, customer.getCustomerStockNum("PKOBP"), dataAccuracy);
	}		
	
	@Test
	public void shouldAdd60Stocks() {
		customer.buyToday("KGHM", 30);
		customer.buyToday("KGHM", 30);
		assertEquals(60, customer.getCustomerStockNum("KGHM"), dataAccuracy);
	}	
	
	@Test
	public void shouldntBuyMoreThanInWallet() {
		customer.buyToday("KGHM", 30);
		customer.buyToday("KGHM", 30);
		assertEquals(60, customer.getCustomerStockNum("KGHM"), dataAccuracy);
	}		
	
}
