package milech.algorithm;

import java.util.Map;

import milech.entity.Wallet;
import milech.repository.StockMarket;

public interface StockAlgorithm {

	Map<String, Integer> chooseStocksToBuy(StockMarket subStockMarket, Wallet wallet);

	Map<String, Integer> chooseStocksToSell(StockMarket subStockMarket, Map<String, Integer> customerStocks);

}
