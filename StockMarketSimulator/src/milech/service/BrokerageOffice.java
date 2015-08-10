package milech.service;

public interface BrokerageOffice {

	float buy(int stockNum, int stockIndex);

	float sell(int stockNum, int stockIndex);

	float getPrice(int stockIndex);
	
}
