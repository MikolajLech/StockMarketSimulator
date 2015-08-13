package milech.customer;

import java.util.Map;

import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;

public interface Customer {
	float buy(int stockNum, int stockIndex);
	float sell(int stockNum, int stockIndex);
	StockAlgorithm chooseAlg(int algNum);
	Stock buyToday();
	Stock sellToday();
	void prtCustomerStocks();
	float getMoney();
	int getCustomerStocksSize();
	Map<Integer, Integer> getCustomerStocks();
	void sellAll();
}