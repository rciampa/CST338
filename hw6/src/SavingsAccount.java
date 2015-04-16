/**
 * This is the Savings account class which extends
 * the Account class
 * 
 * @author rciampa
 *
 */
public class SavingsAccount extends Account {
	private double interestRate;
	private double interestPayment;
	
	SavingsAccount() {
		super();
		interestRate = 0;
	}
	
	/**
	 * Constructor that sets the balance for the account
	 * and the interest rate
	 * 
	 * @param balance The opening balance
	 * @param interestRate The rate to set
	 */
	SavingsAccount(double balance, double interestRate) {
		super(balance);
		this.interestRate = interestRate;
	}
	
	/**
	 * Sets the interest payment
	 */
	public void setInterestPayment() {
		double amount = getBalance() + getInterestPayment();
		setBalance(amount);
	}
	
	/**
	 * Gets the interest payment
	 * @return
	 */
	public double getInterestPayment() {
		interestPayment = getBalance() * interestRate;
		return interestPayment;
	}
	
	
}

