package milech.entity;

public class Wallet {
	float money;
	
	public Wallet(int money) {
		this.money = money;
	}

	public float getMoney() {
		return money;
	}
	
	public void addMoneyToWallet(float money) {
		this.money += money;		
	}
	
	public void takeMoneyFromWallet(float money) {
		this.money -= money;		
	}
}
