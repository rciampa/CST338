
public class CheckingAccount extends Account {
	private double monthlyFee;
	
	//Default constructor
	CheckingAccount() {
		super();
		monthlyFee = 0;
	}
	
	/**
	 * Constructor with balance and checking fee
	 * @param balance The opening balance
	 * @param monthlyFee The monthly fee
	 */
	CheckingAccount(double balance, double fee) {
		super(balance);
		this.monthlyFee = fee;
	}
	
	/**
	 * Deducts the checking fee
	 * 
	 * @param counter
	 * @return
	 */
	public double chargeFee(int counter) {
		double balance = getBalance() - (monthlyFee * counter);
		setBalance(balance);
		return monthlyFee;
	}
}

