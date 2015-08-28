package milech.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import milech.entity.Stock;
import milech.stockMarketHelper.StockMarketHelper;

import org.junit.Before;
import org.junit.Test;

public class StockMarketTest {
	//TODO use spring annotations
	StockMarket stockMarket;
	String dataTest = "resources/data.csv";
	
	@Before
	public void initTest() {
		stockMarket = new StockMarketMapImpl(dataTest);
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
		testMap.put(StockMarketHelper.parseDate("20130102"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130103", "37.35"));
		testDay.add(new Stock("KGHM", "20130103", "193.5"));
		testDay.add(new Stock("PGNIG", "20130103", "5.25"));
		testDay.add(new Stock("JSW", "20130103", "96.4"));
		testDay.add(new Stock("TPSA", "20130103", "12.13"));
		testMap.put(StockMarketHelper.parseDate("20130103"), testDay);
		
		stockMarket.moveXDaysForward(2);
		
		assertEquals(new StockMarketMapImpl(testMap).toString(), 
				stockMarket.getStockMarketTillToday().toString());
	}
	
	@Test
	public void shouldGet3StocksTillToday() {
		Map<Date, List<Stock>> testMap = new TreeMap<Date, List<Stock>>();
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130102", "37.35"));
		testDay.add(new Stock("KGHM", "20130102", "193.1"));
		testDay.add(new Stock("PGNIG", "20130102", "5.26"));
		testDay.add(new Stock("JSW", "20130102", "94.6"));
		testDay.add(new Stock("TPSA", "20130102", "12.16"));
		testMap.put(StockMarketHelper.parseDate("20130102"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130103", "37.35"));
		testDay.add(new Stock("KGHM", "20130103", "193.5"));
		testDay.add(new Stock("PGNIG", "20130103", "5.25"));
		testDay.add(new Stock("JSW", "20130103", "96.4"));
		testDay.add(new Stock("TPSA", "20130103", "12.13"));
		testMap.put(StockMarketHelper.parseDate("20130103"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130104", "36.7"));
		testDay.add(new Stock("KGHM", "20130104", "193.5"));
		testDay.add(new Stock("PGNIG", "20130104", "5.28"));
		testDay.add(new Stock("JSW", "20130104", "95.8"));
		testDay.add(new Stock("TPSA", "20130104", "12.5"));
		testMap.put(StockMarketHelper.parseDate("20130104"), testDay);
		
		stockMarket.moveXDaysForward(3);
		
		assertEquals(new StockMarketMapImpl(testMap).toString(), 
				stockMarket.getStockMarketTillToday().toString());
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
	public void shouldGetCurrentDay2() {
		stockMarket.moveXDaysForward(14);
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130121", "35.77"));
		testDay.add(new Stock("KGHM", "20130121", "188.2"));
		testDay.add(new Stock("PGNIG", "20130121", "5.75"));
		testDay.add(new Stock("JSW", "20130121", "93.8"));
		testDay.add(new Stock("TPSA", "20130121", "12.68"));
		assertEquals(testDay.toString(), stockMarket.getCurrentDay().toString());
	}
	
	@Test
	public void shouldGetKGHMHistory() {
		stockMarket.moveXDaysForward(7);
		List<Stock> pkobpHistory = new ArrayList<Stock>();
		pkobpHistory.add(new Stock("KGHM", "20130102", "193.1"));
		pkobpHistory.add(new Stock("KGHM", "20130103", "193.5"));
		pkobpHistory.add(new Stock("KGHM", "20130104", "193.5"));
		pkobpHistory.add(new Stock("KGHM", "20130107", "193"));
		pkobpHistory.add(new Stock("KGHM", "20130108", "193.1"));
		pkobpHistory.add(new Stock("KGHM", "20130109", "191"));
		pkobpHistory.add(new Stock("KGHM", "201301010", "192"));
		
		assertEquals(pkobpHistory.toString(), stockMarket.getStockMarketTillToday().getStockHistory("KGHM").toString());
	}
	
	@Test
	public void shouldGetNextDay() {
		stockMarket.moveToNextDay();
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
	public void shouldGetPKOBPHistory() {
		stockMarket.moveXDaysForward(4);
		List<Stock> pkobpHistory = new ArrayList<Stock>();
		pkobpHistory.add(new Stock("PKOBP", "20130102", "37.35"));
		pkobpHistory.add(new Stock("PKOBP", "20130103", "37.35"));
		pkobpHistory.add(new Stock("PKOBP", "20130104", "36.7"));
		pkobpHistory.add(new Stock("PKOBP", "20130107", "36.13"));
		
		assertEquals(pkobpHistory.toString(), stockMarket.getStockMarketTillToday().getStockHistory("PKOBP").toString());
	}
	
	@Test
	public void shouldGetStockLast2DaysTillToday() { // Don't take more days than in stockMarket
		Map<Date, List<Stock>> testMap = new TreeMap<Date, List<Stock>>();
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130102", "37.35"));
		testDay.add(new Stock("KGHM", "20130102", "193.1"));
		testDay.add(new Stock("PGNIG", "20130102", "5.26"));
		testDay.add(new Stock("JSW", "20130102", "94.6"));
		testDay.add(new Stock("TPSA", "20130102", "12.16"));
		testMap.put(StockMarketHelper.parseDate("20130102"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130103", "37.35"));
		testDay.add(new Stock("KGHM", "20130103", "193.5"));
		testDay.add(new Stock("PGNIG", "20130103", "5.25"));
		testDay.add(new Stock("JSW", "20130103", "96.4"));
		testDay.add(new Stock("TPSA", "20130103", "12.13"));
		testMap.put(StockMarketHelper.parseDate("20130103"), testDay);
		
		stockMarket.moveXDaysForward(2);
		
		assertEquals(new StockMarketMapImpl(testMap).toString(), 
				stockMarket.getStockLastXDaysTillToday(3).toString());
	}
	
	@Test
	public void shouldGetStockLast3DaysTillToday() {
		Map<Date, List<Stock>> testMap = new TreeMap<Date, List<Stock>>();
		List<Stock> testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130108", "35.6"));
		testDay.add(new Stock("KGHM", "20130108", "193.1"));
		testDay.add(new Stock("PGNIG", "20130108", "5.27"));
		testDay.add(new Stock("JSW", "20130108", "93.5"));
		testDay.add(new Stock("TPSA", "20130108", "12.73"));
		testMap.put(StockMarketHelper.parseDate("20130108"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130109", "35.99"));
		testDay.add(new Stock("KGHM", "20130109", "191"));
		testDay.add(new Stock("PGNIG", "20130109", "5.25"));
		testDay.add(new Stock("JSW", "20130109", "93.7"));
		testDay.add(new Stock("TPSA", "20130109", "12.75"));
		testMap.put(StockMarketHelper.parseDate("20130109"), testDay);
		
		testDay = new ArrayList<Stock>();
		testDay.add(new Stock("PKOBP", "20130110", "36"));
		testDay.add(new Stock("KGHM", "20130110", "192"));
		testDay.add(new Stock("PGNIG", "20130110", "5.43"));
		testDay.add(new Stock("JSW", "20130110", "94.5"));
		testDay.add(new Stock("TPSA", "20130110", "12.79"));
		testMap.put(StockMarketHelper.parseDate("20130110"), testDay);
		
		stockMarket.moveXDaysForward(7);
		
		assertEquals(new StockMarketMapImpl(testMap).toString(), 
				stockMarket.getStockLastXDaysTillToday(3).toString());
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
	public void shouldGetStockMarketDaysNum() {
		assertEquals(247, stockMarket.getStockMarketDaysNum());
	}
	
	@Test
	public void shouldGetSubStockMarketDaysNum() {
		stockMarket.moveXDaysForward(7);
		assertEquals(4, stockMarket.getStockLastXDaysTillToday(4).getStockMarketDaysNum());
	}
	
	@Test
	public void shouldGetSubStockMarketTillTodayDaysNum() {
		stockMarket.moveXDaysForward(14);
		assertEquals(14, stockMarket.getStockMarketTillToday().getStockMarketDaysNum());
	}
	
}
