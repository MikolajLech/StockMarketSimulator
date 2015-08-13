package milech.algorithm;

import milech.entity.Stock;
import milech.repository.StockMarket;

public interface StockAlgorithm {

	Stock chooseStockToBuy(StockMarket stockMarket);

//	Stock chooseStockToSell(Customer customer);

}
