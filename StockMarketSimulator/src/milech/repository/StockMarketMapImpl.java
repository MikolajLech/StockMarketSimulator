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

import com.google.common.collect.Maps;
import com.google.common.collect.Range;

public class StockMarketMapImpl implements StockMarket {

	private SortedMap<Date, List<Stock>> stocks = new TreeMap<Date, List<Stock>>(); // already sorted
	private LinkedList<Date> days;
	private ListIterator iterator;
	private Reader csvReader;
	
	public StockMarketMapImpl(String dataSource) {
		csvReader = new CsvReader(dataSource);
		loadAllData();
		init();
	}
	
	private void init() {
		days = new LinkedList<Date>(stocks.keySet());
		iterator = days.listIterator();
	}
	
	public StockMarketMapImpl(Map<Date, List<Stock>> stockMarketMap) {
		stocks.putAll(stockMarketMap);
		init();
	}
	
	public int getStockSize() {
		return stocks.size();
	}
	
	public void loadAllData() {
		while(loadNextStock() != null) {}
	}
	
	public boolean moveToNextDay() {
		if(iterator.hasNext()) {
			iterator.next();
			return true;
		}
		return false;
	}
	
	private boolean backToPreviousDay() {
		if(iterator.hasPrevious()) {
			iterator.previous();
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
	
	public List<Stock> getCurrentDay() {
		List<Stock> day = null;
		if(iterator.hasPrevious()) {
			day = stocks.get(iterator.previous());
			iterator.next();
		}
		return day;
	}
	
	public StockMarket getStockMarketTillToday() {
		Map<Date, List<Stock>> stocksTillToday =
				Maps.filterKeys(stocks, Range.closed(stocks.firstKey(), getCurrentDay().get(0).getDate()));
		return new StockMarketMapImpl(stocksTillToday);
	}
	
	public StockMarketMapImpl() {}
	
	public Stock loadNextStock() {
		Stock nextStock = getNextStock();
		return add(nextStock);
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

	public void setDataSource(String dataSource) {
		csvReader = new CsvReader(dataSource);
	}

	public Stock add(Stock stock) {
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
