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
	String dataTest = "resources/data.csv";
	float dataAccuracy = 0.01f;
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
		brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		customer = new CustomerImpl(brokerageOffice);
	}
	
	@Test
	public void shouldGetMoney10000() {
		assertEquals(10000, customer.getMoney(), dataAccuracy);
	}	
	
	@Test
	public void shouldSellAllCustomerStocksFor373_5() {
		stockMarket.moveToNextDay();
		customer.buySameStock("PKOBP", 10);
		assertEquals(373.5 , customer.sellAll(), dataAccuracy);
	}
	
	//tests buySameStock() and sellSameStock()
	@Test
	public void shouldSellAllCustomerStocksFor2409_7() {
		stockMarket.moveToNextDay();
		customer.buySameStock("PKOBP", 10);
		customer.buySameStock("KGHM", 10);
		customer.buySameStock("PGNIG", 20);
		assertEquals(2409.7 , customer.sellAll(), dataAccuracy);
	}
	
	@Test
	public void shouldBuyPGNIG_TPSAWithAlgorithm() {
		stockMarket.moveXDaysForward(7);
		customer.buyWithAlgorithm();
		System.out.println(stockMarket.getStockMarketTillToday().getStockHistory("PKOBP").toString());
		System.out.println(stockMarket.getStockMarketTillToday().getStockHistory("KGHM").toString());
		System.out.println(stockMarket.getStockMarketTillToday().getStockHistory("PGNIG").toString());
		System.out.println(stockMarket.getStockMarketTillToday().getStockHistory("JSW").toString());
		System.out.println(stockMarket.getStockMarketTillToday().getStockHistory("TPSA").toString());
		//customer has pgnig and tpsa in stocks
		
	}
	
}
