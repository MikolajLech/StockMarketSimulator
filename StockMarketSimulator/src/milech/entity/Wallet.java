package milech.entity;

import milech.parser.Parser;


public class Wallet {
	float money;
	
	public Wallet(int money) {
		this.money = money;
	}

	public float getMoney() {
		money = Parser.round(money, 2);
		return money;
	}
	
	public void addMoneyToWallet(float money) {
		this.money += money;		
	}
	
	public void takeMoneyFromWallet(float money) {
		this.money -= money;		
	}
	
}
