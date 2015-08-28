package milech.stockMarketHelper;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.GregorianCalendar;

import milech.customer.Customer;
import milech.customer.CustomerImpl;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;
import milech.stockMarketHelper.StockMarketHelper;

import org.junit.Before;
import org.junit.Test;

public class StockMarketHelperTest {
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
	
	// test parser
	@Test
	public void shouldConv1000To20() {
		stockMarket.moveXDaysForward(1);
		assertEquals(20 , StockMarketHelper.howManyStocksForThatMoney(1000, 50));
	}
	
	@Test
	public void shouldConv10000To50() {
		stockMarket.moveXDaysForward(1);
		assertEquals(200 , StockMarketHelper.howManyStocksForThatMoney(10000, 50));
	}	
	
	@Test
	public void shouldParseDate() {
		GregorianCalendar calendar = new GregorianCalendar(2013, 0, 2); // month is 0 based
		assertEquals(calendar.getTimeInMillis(), StockMarketHelper.parseDate("20130102").getTime());
	}
	
	@Test
	public void shouldParseDate2() {
		GregorianCalendar calendar = new GregorianCalendar(2013, 0, 10); // month is 0 based
		assertEquals(calendar.getTimeInMillis(), StockMarketHelper.parseDate("20130110").getTime());
	}
	
	@Test
	public void shouldParsePrice() {
		assertEquals(37.35, StockMarketHelper.parsePrice("37.35"), dataAccuracy);
	}
	
	@Test
	public void shouldParsePrice2() {
		assertEquals(59.69, StockMarketHelper.parsePrice("59.69"), dataAccuracy);
	}
	
	@Test
	public void shouldRound() {
		assertEquals(37.23, StockMarketHelper.round(37.2346f, 2), dataAccuracy);
	}
	
	@Test
	public void shouldRound2() {
		assertEquals(46.55, StockMarketHelper.round(46.54914f, 2), dataAccuracy);
	}
	
	@Test
	public void shouldRound3() {
		assertEquals(0, StockMarketHelper.round(0, 2), dataAccuracy);
	}
	
}
