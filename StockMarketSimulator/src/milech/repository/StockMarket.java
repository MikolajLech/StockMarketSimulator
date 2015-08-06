package milech.repository;

import milech.entity.Stock;

public interface StockMarket {

	void loadNextStock();

	void setDataSource(String dataSource);

	Stock getStock(int index);

}