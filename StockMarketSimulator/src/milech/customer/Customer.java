package milech.customer;

import milech.algorithm.StockAlgorithm;
import milech.entity.Stock;

public interface Customer {
	float buy(int stockNum, String companyName);
	float sell(int stockNum, String companyName);
	StockAlgorithm chooseAlg(int algNum);
	public Stock buyToday(String stockToBuyName, int howMuch);
//	Stock sellToday();
	float getMoney();
	float sellAll();
	public String toString();

}