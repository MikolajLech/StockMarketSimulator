package milech.repository;

import java.util.List;

import milech.entity.Stock;

public interface StockMarket {

	Stock add(Stock stock);
	public boolean moveXDaysForward(int howManyDays);
	public boolean moveToNextDay();
	String toString();
	void setDataSource(String dataSource);
	public List<Stock> getCurrentDay();
	public int getStockSize();
	public StockMarket getStockMarketTillToday();
	
}