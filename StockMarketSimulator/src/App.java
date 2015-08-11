import milech.customer.Customer;
import milech.customer.CustomerImpl;
import milech.repository.StockMarket;
import milech.repository.StockMarketImpl;
import milech.service.BrokerageOffice;
import milech.service.BrokerageOfficeImpl;


public class App {
	public static void main(String[] args) {
		String dataTest = "resources/data.csv";
		StockMarket stockMarket = new StockMarketImpl(dataTest);
		BrokerageOffice brokerageOffice = new BrokerageOfficeImpl(stockMarket);
		Customer customer = new CustomerImpl(brokerageOffice);
		
		for(int i = 0; i < 2; i++) {
			stockMarket.loadNextDay();
			customer.buyToday();
		}
		customer.prtCustomerStocks();
		System.out.println("money: " + customer.getMoney());
	}
}
