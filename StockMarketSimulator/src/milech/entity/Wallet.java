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
	
	public float takeMoneyFromWallet(float money) {
		if(money > this.money) {
			float resultMoney = this.money;
			this.money = 0;
			return resultMoney;
		}
		else {
			this.money -= money;
			return money;
		}
	}

	public boolean ifWalletCanAfford(float wholeStockCost) {
		if(money >= wholeStockCost) {
			return true;
		}
		return false;
	}
	
}
