package milech.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import milech.entity.Stock;
import milech.reader.CsvReader;
import milech.reader.Reader;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.google.common.collect.Range;

@Repository("stockMarket")
public class StockMarketMapImpl implements StockMarket {

	private SortedMap<Date, List<Stock>> stocks = new TreeMap<Date, List<Stock>>(); // already sorted
	private LinkedList<Date> days;
	private ListIterator iterator;
	private Reader csvReader;
	
	public StockMarketMapImpl() {
		initCsvReader();
		loadAllData();
		init();
	}
	
	public StockMarketMapImpl(Map<Date, List<Stock>> stockMarketMap) {
		stocks.putAll(stockMarketMap);
		init();
	}
	
	public StockMarketMapImpl(String dataSource) {
		csvReader = new CsvReader(dataSource);
		loadAllData();
		init();
	}
	
	
	private Stock addStock(Stock stock) {
		if(stock == null) {
			return null;
		}
		if(stocks.get(stock.getDate()) == null) {
			List<Stock> newDay = new ArrayList<Stock>();
			newDay.add(stock);
			stocks.put(stock.getDate(), newDay);
		}
		else {
			List<Stock> stockListToPut = stocks.get(stock.getDate());
			stockListToPut.add(stock);
			stocks.put(stock.getDate(), stockListToPut);
		}
		return stock;
	}
	
//	private boolean backToPreviousDay() {
//		if(iterator.hasPrevious()) {
//			iterator.previous();
//			return true;
//		}
//		return false;
//	}
	
	private boolean dayHasStock(Date date, String stockName) {
		List<Stock> day = stocks.get(date);
		for(Stock stock : day) {
			if(stock.getName().equals(stockName)){
				return true;
			}
		}
		return false;
	}
	
	public List<Stock> getCurrentDay() {
		List<Stock> day = null;
		if(iterator.hasPrevious()) {
			day = stocks.get(iterator.previous());
			iterator.next();
		}
		return day;
	}
	
	private Date getCurrentDayDate() {
		Date date = null;
		if(iterator.hasPrevious()) {
			date = (Date)iterator.previous();
			iterator.next();
		}
		return date;
	}
	
	private Stock getNextStock() {
		String line = "";
		String csvSplitBy = ",";
		Stock newStock = new Stock();
		if((line = csvReader.getNextLine()) != null) {
			// use comma as separator
			String[] stockData = line.split(csvSplitBy);
			newStock.setName(stockData[0]);
			newStock.setDate(stockData[1]);
			newStock.setPrice(stockData[2]);
			return newStock;
		}
		return null;
	}
	
	private Stock getStockFromDay(List<Stock> day, String stockName) {
		for(Stock stock : day) {
			if(stock.getName().equals(stockName)) {
				return stock;
			}
		}
		return null;
	}
	
	public List<Stock> getStockHistory(String stockName) {
		List<Stock> stockHistory = new ArrayList<Stock>();
		Stock currentDayStock = null;
		moveToDayZero();
		while(moveToNextDay()) {
			if(dayHasStock(getCurrentDayDate(), stockName)) {
				currentDayStock = getStockFromDay(stocks.get(getCurrentDayDate()), stockName);
				stockHistory.add(currentDayStock);
			}
		}		
		return stockHistory;
	}
	
	public int getStockMarketSize() {
		return stocks.size();
	}
	
	public StockMarket getStockMarketTillToday() {
		Map<Date, List<Stock>> stocksTillToday =
				Maps.filterKeys(stocks, Range.closed(stocks.firstKey(), getCurrentDay().get(0).getDate()));
		StockMarketMapImpl stockMakretTillToday = new StockMarketMapImpl(stocksTillToday);
		stockMakretTillToday.moveToLastDay();
		return stockMakretTillToday;
	}
	
	private void init() {
		days = new LinkedList<Date>(stocks.keySet());
		moveToDayZero();
	}
	
	//	@Value("#{fileName}")
	private void initCsvReader() {
		csvReader = new CsvReader("resources/data.csv");
	}
	
	private void loadAllData() {
		while(loadNextStock() != null) {}
	}
	
	private Stock loadNextStock() {
		Stock nextStock = getNextStock();
		return addStock(nextStock);
	}

	private void moveToDayZero() {
		iterator = days.listIterator();
	}

	private void moveToLastDay() {
		while(moveToNextDay());
	}
	
	public boolean moveToNextDay() {
		if(iterator.hasNext()) {
			iterator.next();
			return true;
		}
		return false;
	}

	public boolean moveXDaysForward(int howManyDays) {
		boolean ifLoadedWell = true;
		for(int i = 0; i < howManyDays && ifLoadedWell; i++) {
			ifLoadedWell = moveToNextDay();
		}
		return ifLoadedWell;
	}

	public void setDataSource(String dataSource) {
		csvReader = new CsvReader(dataSource);
	}
	
	public String toString() {
		String outStr = "";
		for(Date marketDay : stocks.keySet()) {
			for(Stock stock : stocks.get(marketDay)) {
				outStr += stock.toString();
			}
		}
		return outStr;
	}

}
