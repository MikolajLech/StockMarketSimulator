package milech.algorithm;

import java.util.Map;

import milech.entity.Wallet;
import milech.repository.StockMarket;
import milech.service.BrokerageOffice;

public interface StockAlgorithm {

	Map<String, Integer> chooseStocksToBuy(StockMarket subStockMarket, BrokerageOffice brokerageOffice, Wallet wallet);

	Map<String, Integer> chooseStocksToSell(StockMarket subStockMarket, Map<String, Integer> customerStocks);

}
