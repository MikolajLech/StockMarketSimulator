package milech.algorithm;

import java.util.Map;

import milech.entity.Wallet;
import milech.repository.StockMarket;

public interface StockAlgorithm {

	Map<String, Integer> chooseStocksToBuy(StockMarket stockMarketTillToday, Wallet wallet);

	Map<String, Integer> chooseStocksToSell(Map<String, Integer> customerStocks);

}
