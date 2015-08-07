package milech.repository;

import milech.entity.Stock;

public interface StockMarket {

	Stock loadNextStock();

	void setDataSource(String dataSource);

	Stock getStock(int index);

	boolean equals(StockMarket compStockMarket);

	int getSize();

	void loadNextDay();

	void add(Stock stock);
	
	void prtStockMarket();

}