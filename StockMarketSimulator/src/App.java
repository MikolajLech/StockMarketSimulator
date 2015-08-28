import milech.customer.Customer;
import milech.repository.StockMarket;
import milech.service.BrokerageOffice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		// String dataFile = "resources/data.csv";
	    StockMarket stockMarket = (StockMarket) context.getBean("stockMarket");
		BrokerageOffice brokerageOffice = (BrokerageOffice) context.getBean("brokerageOffice");
		Customer customer = (Customer) context.getBean("customer");

		while (stockMarket.moveToNextDay()) {
			customer.sellWithAlgorithm();
			customer.buyWithAlgorithm();
		}

		System.out.println("Customers stocks: \n" + customer.toString());
		System.out.println("Left Money: " + customer.getMoney());
		System.out.println("Money from selling: " + customer.sellAll());
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
