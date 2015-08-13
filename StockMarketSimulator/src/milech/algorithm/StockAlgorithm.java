package milech.algorithm;

import java.util.Map;

import milech.entity.Stock;
import milech.repository.StockMarket;

public interface StockAlgorithm {

	Stock chooseStockToBuy(StockMarket stockMarket);

	String chooseStockToSell(Map<String, Integer> customerStocks);

}
