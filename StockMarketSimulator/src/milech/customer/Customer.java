package milech.customer;

import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;

public interface Customer {
	float buy(int stockNum, String companyName);
	float sell(int stockNum, String companyName);
	StockAlgorithm chooseAlg(int algNum);
	Stock buyToday(String stockToBuyName, int howMuch);
	Stock sellToday(String stockToBuyName, int howMuch);
	float getMoney();
	float sellAll();
	String toString();
	void buyWithAlgorithm();
	void sellWithAlgorithm();
}