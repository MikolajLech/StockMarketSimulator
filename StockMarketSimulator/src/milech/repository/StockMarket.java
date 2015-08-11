package milech.repository;

import milech.entity.Stock;

public interface StockMarket {

	void add(Stock stock);

	boolean equals(StockMarket compStockMarket);

	int getSize();

	Stock getStock(int index);

	void loadNextDay();

	Stock loadNextStock();

	void prtStockMarket();
	
	void setDataSource(String dataSource);

	Stock loadNextStocks(int stocksNum);

	int getCurrDaySize();

}