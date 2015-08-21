package milech.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import milech.entity.Stock;
import milech.parser.Parser;

import org.junit.Before;
import org.junit.Test;

public class StockMarketTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	String dataTest = "resources/dataTest.csv";
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
	}
	
	@Test
	public void shouldGetNextDay() {
		stockMarket.moveXDaysForward(1);
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130102", "37.35"));
		testDay.add(new Stock("KGHM", "20130102", "193.1"));
		testDay.add(new Stock("PGNIG", "20130102", "5.26"));
		testDay.add(new Stock("JSW", "20130102", "94.6"));
		testDay.add(new Stock("TPSA", "20130102", "12.16"));
		assertEquals(testDay.toString(), stockMarket.getCurrentDay().toString());
	}
	
	@Test
	public void shouldGetNextNextDay() {
		stockMarket.moveXDaysForward(1);
		stockMarket.moveXDaysForward(1);
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130103", "37.35"));
		testDay.add(new Stock("KGHM", "20130103", "193.5"));
		testDay.add(new Stock("PGNIG", "20130103", "5.25"));
		testDay.add(new Stock("JSW", "20130103", "96.4"));
		testDay.add(new Stock("TPSA", "20130103", "12.13"));
		assertEquals(testDay.toString(), stockMarket.getCurrentDay().toString());
	}
	
	@Test
	public void shouldGetThirdNextDay() {
		stockMarket.moveXDaysForward(1);
		stockMarket.moveXDaysForward(1);
		stockMarket.moveXDaysForward(1);
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130104", "36.7"));
		testDay.add(new Stock("KGHM", "20130104", "193.5"));
		testDay.add(new Stock("PGNIG", "20130104", "5.28"));
		testDay.add(new Stock("JSW", "20130104", "95.8"));
		testDay.add(new Stock("TPSA", "20130104", "12.5"));
		assertEquals(testDay.toString(), stockMarket.getCurrentDay().toString());
	}
	
	@Test
	public void shouldGetCurrentDay() {
		stockMarket.moveXDaysForward(1);
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130102", "37.35"));
		testDay.add(new Stock("KGHM", "20130102", "193.1"));
		testDay.add(new Stock("PGNIG", "20130102", "5.26"));
		testDay.add(new Stock("JSW", "20130102", "94.6"));
		testDay.add(new Stock("TPSA", "20130102", "12.16"));
		assertEquals(testDay.toString(), stockMarket.getCurrentDay().toString());
	}
	
	@Test
	public void shouldGet2StocksTillToday() {
		Map<Date, List<Stock>> testMap = new TreeMap<Date, List<Stock>>();
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130102", "37.35"));
		testDay.add(new Stock("KGHM", "20130102", "193.1"));
		testDay.add(new Stock("PGNIG", "20130102", "5.26"));
		testDay.add(new Stock("JSW", "20130102", "94.6"));
		testDay.add(new Stock("TPSA", "20130102", "12.16"));
		testMap.put(Parser.parseDate("20130102"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130103", "37.35"));
		testDay.add(new Stock("KGHM", "20130103", "193.5"));
		testDay.add(new Stock("PGNIG", "20130103", "5.25"));
		testDay.add(new Stock("JSW", "20130103", "96.4"));
		testDay.add(new Stock("TPSA", "20130103", "12.13"));
		testMap.put(Parser.parseDate("20130103"), testDay);
		
		stockMarket.moveXDaysForward(2);
		
		assertEquals(new StockMarketMapImpl(testMap).toString(), 
				stockMarket.getStockMarketTillToday().toString());
	}
	
}
