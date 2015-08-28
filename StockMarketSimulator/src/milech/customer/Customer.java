package milech.customer;


public interface Customer {
	float getMoney();
	float sellAll();
	String toString();
	void buyWithAlgorithm();
	void sellWithAlgorithm();
	void buySameStock(String stockToBuyName, int howManyStocks);
	float sellSameStock(String stockToSellName, int howManyStocks);

}