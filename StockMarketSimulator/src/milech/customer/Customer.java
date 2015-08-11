package milech.customer;

import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;

public interface Customer {
	float buy(int stockNum, int stockIndex);
	float sell(int stockNum, int stockIndex);
	StockAlgorithm chooseAlg(int algNum);
	Stock buyToday();
	void prtCustomerStocks();
	float getMoney();
}