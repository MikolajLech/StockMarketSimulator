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
		stockMarket.moveXDaysForward(1);
		customer = new CustomerImpl(brokerageOffice);
	}
	
	@Test
	public void shouldGetMoney10000() {
		assertEquals(10000, customer.getMoney(), dataAccuracy);
	}	
	
	
}
