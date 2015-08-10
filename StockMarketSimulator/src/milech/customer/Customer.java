package milech.customer;

public interface Customer {
	float buy(int stockNum, int stockIndex);
	float sell(int stockNum, int stockIndex);
}