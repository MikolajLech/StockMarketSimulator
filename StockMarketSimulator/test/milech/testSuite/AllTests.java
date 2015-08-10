package milech.testSuite;
import milech.customer.CustomerTest;
import milech.repository.StockMarketTest;
import milech.service.BrokerageOfficeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StockMarketTest.class, BrokerageOfficeTest.class, CustomerTest.class })
public class AllTests {

}
