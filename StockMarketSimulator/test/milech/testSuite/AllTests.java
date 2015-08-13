package milech.testSuite;
import milech.customer.CustomerTest;
import milech.repository.StockMarketTest;
import milech.service.BrokerageOfficeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import parser.ParserTest;

@RunWith(Suite.class)
@SuiteClasses({ StockMarketTest.class, BrokerageOfficeTest.class, CustomerTest.class, ParserTest.class })
public class AllTests {

}
