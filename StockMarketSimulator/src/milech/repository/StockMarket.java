package milech.repository;

import java.util.List;

import milech.entity.Stock;

public interface StockMarket {

	public boolean moveXDaysForward(int howManyDays);
	public boolean moveToNextDay();
	String toString();
	void setDataSource(String dataSource);
	public List<Stock> getCurrentDay();
	public int getStockMarketDaysNum();
	public StockMarket getStockMarketTillToday();
	public List<Stock> getStockHistory(String stockName);
	public void moveToDayZero();
	public StockMarket getStockLastXDaysTillToday(int howManyDays);
}