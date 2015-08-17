package milech.algorithm;

import java.util.Map;

import milech.repository.StockMarket;

public interface StockAlgorithm {

	String chooseStockToBuy(StockMarket stockMarket);

	String chooseStockToSell(Map<String, Integer> customerStocks);

	int buyForHowMuchMoney(int money);

	int sellForHowMuchMoney(int money);

}
