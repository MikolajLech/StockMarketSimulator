package milech.repository;

import java.util.List;

import milech.entity.Stock;

public interface StockMarket {

	Stock add(Stock stock);

	boolean getNextDay();

	Stock loadNextStock();

	String toString();
	
	void setDataSource(String dataSource);
	
	public List<Stock> getCurrentDay();
	
}