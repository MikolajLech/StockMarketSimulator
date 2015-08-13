package milech.algorithm;

import milech.customer.Customer;
import milech.repository.StockMarket;

public interface StockAlgorithm {

	int chooseStockToBuy(StockMarket stockMarket);

	int chooseStockToSell(Customer customer);

}
