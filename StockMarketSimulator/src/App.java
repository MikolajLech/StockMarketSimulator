import milech.customer.Customer;
import milech.customer.CustomerImpl;
import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;


public class App {
	public static void main(String[] args) {
		String dataFile = "resources/data.csv";
		StockMarket stockMarket = new StockMarketMapImpl(dataFile);
		BrokerageOffice brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		Customer customer = new CustomerImpl(brokerageOffice);
		
		while(stockMarket.getNextDay()) { 
			customer.sellWithAlgorithm();
			customer.buyWithAlgorithm();                                                     
		}
		
		System.out.println("Left Money: " + customer.getMoney());
		System.out.println("Money from selling: " + customer.sellAll());
		System.out.println();
		
//		System.out.println(stockMarket.toString());
	}
}
