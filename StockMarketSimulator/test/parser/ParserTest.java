package parser;

import static org.junit.Assert.assertEquals;
import milech.customer.Customer;
import milech.customer.CustomerImpl;
import milech.parser.Parser;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {
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
	
	// test parser
	@Test
	public void shouldConv1000To20() {
		assertEquals(20 , Parser.convMoneyToStock(1000, 50));
	}
	
	@Test
	public void shouldConv10000To50() {
		assertEquals(200 , Parser.convMoneyToStock(10000, 50));
	}	
	
	
}
