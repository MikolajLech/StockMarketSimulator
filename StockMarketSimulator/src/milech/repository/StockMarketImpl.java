package milech.repository;

import java.util.ArrayList;
import java.util.List;

import milech.entity.Stock;
import milech.reader.Reader;
import milech.reader.CsvReader;

public class StockMarketImpl implements StockMarket {

	private List<Stock> stocks = new ArrayList<Stock>();
	private Stock firstStockInDay;
	private Reader csvReader;
	
	public StockMarketImpl(String dataSource) {
		csvReader = new CsvReader(dataSource);
	}
	
	public StockMarketImpl() {}
	
	@Override
	public boolean equals(StockMarket compStockMarket) {
		if(this.getSize() != compStockMarket.getSize())
			return false;
		for(int i = 0; i < this.getSize(); i++) {
			if(!this.stocks.get(i).equals(compStockMarket.getStock(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int getSize() {
		return stocks.size();
	}
	
	@Override
	public Stock getStock(int index) {
		return stocks.get(index); 
	}
	
	@Override
	public Stock loadNextStock() {
		Stock nextStock = getNextStock();
		stocks.add(nextStock);
		return nextStock;
	}
	
	@Override
	public Stock loadNextStocks(int stocksNum) {
		Stock nextStock = null;
		for(int i = 0; i < stocksNum; i++) {
			nextStock = getNextStock();	
			stocks.add(nextStock);		
		}
		return nextStock;
	}
	
	private Stock getNextStock() {
		String line = "";
		String csvSplitBy = ",";
		Stock newStock = new Stock();
//			while ((line = br.readLine()) != null) {
		if((line = csvReader.getNextLine()) != null) {
			// use comma as separator
			String[] stockData = line.split(csvSplitBy);
			newStock.setName(stockData[0]);
			newStock.setDate(stockData[1]);
			newStock.setPrice(stockData[2]);
			}
		return newStock;
	}

	@Override
	public void setDataSource(String dataSource) {
		csvReader = new CsvReader(dataSource);
	}

	@Override
	public void loadNextDay() {
		if(firstStockInDay == null) {
			firstStockInDay = getNextStock();
		}
		stocks.add(firstStockInDay);
		Stock nextStock = getNextStock();
		while(firstStockInDay.getDate().equals(nextStock.getDate())){
			stocks.add(nextStock);
			nextStock = getNextStock();			
		}
		firstStockInDay = nextStock;		
	}

	@Override
	public void add(Stock stock) {
		stocks.add(stock);		
	}
	
	@Override
	public void prtStockMarket() {
		for(Stock stock : stocks) {
			System.out.println(stock.toString());
		}
	}
	
}
