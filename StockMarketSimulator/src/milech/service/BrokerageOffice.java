package milech.service;


public interface BrokerageOffice {

	public float buy(int stockNum, String companyName);

	public float sell(int stockNum, String companyName);
}
