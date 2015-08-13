import milech.repository.StockMarket;
import milech.repository.StockMarketMapImpl;


public class App {
	public static void main(String[] args) {
		String dataTest = "resources/dataTest.csv";
		StockMarket stockMarket = new StockMarketMapImpl(dataTest);
//		BrokerageOffice brokerageOffice = new BrokerageOfficeImpl(stockMarket);
//		Customer customer = new CustomerImpl(brokerageOffice);
		
		System.out.println(stockMarket.toString());
	}
}
