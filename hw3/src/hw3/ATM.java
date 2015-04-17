/**
 * Title: AMT
 * Abstract: The ATM class is designed to hold money for bank clients to
 * 			 withdraw from, deposit into their account or to make transfers
 * 			 from one bank customer to another. Bank customers must enter
 * 			 their name and account PIN to conduct a transaction at an ATM.
 * 			 The ATM keeps track of the number of each type of transaction
 * 			 in addition the number of successful and failed deposits,
 * 			 transfers and withdrawals.
 *			
 *			 Bank employees can add funds to the ATM to service their
 *			 customers and get a status report from the ATM system.
 *			 The bank customer accounts are added to the ATM network
 *			 and are available across all ATM's.
 *
 * @author rciampa
 * ID" 7470
 * Date: 2/7/2015
 * File: ATM.java
 * Requires: Customer.java
 */

package hw3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ATM Class called ATM that simulates an imaginary automated teller
 * machine (ATM) on the CSUMB campus.
 */
public class ATM {

	//
	// Fields
	//

	/**
	 * Holds all of the banks customers names, pin and balance. This is a static
	 * declaration so that the customers are visible to all the objects of this
	 * class.
	 */
	static private List<Customer> customers;
	/**
	 * Holds the current balance of the ATM.
	 */
	private double atmBalance;
	/**
	 * Holds the physical location of the AMT.
	 */
	private String location;
	/**
	 * The serial number of this ATM.
	 */
	private int serialNumber;
	/**
	 * The total number of withdrawals attempted at the ATM.
	 */
	private int withdrawals;
	/**
	 * The total number of failed withdrawals at the ATM.
	 */
	private int withdrawFailed;
	/**
	 * The total number of successful withdrawals at the ATM.
	 */
	private int withdrawSuccess;
	/**
	 * The bank name to which this ATM belongs
	 */
	private String bankName;
	/**
	 * The total number of transactions at this ATM
	 */
	private int numberOfTransactions;
	/**
	 * The total number of deposits at this ATM, succeeded and failed
	 */
	private int depositTotal;
	/**
	 * The total number of deposits that failed
	 */
	private int depositsFailed;
	/**
	 * The total number of deposits that succeeded
	 */
	private int depositsSuccess;
	/**
	 * The number of transfers at this ATM that failed.
	 */
	private int transfersFailed;
	/**
	 * The number of transfers at this ATM that succeeded
	 */
	private int transfersSuccess;
	/**
	 * The total number of transfers at this ATM, succeeded and failed
	 */
	private int transfersTotal;

	//
	// Constructors
	//
	public ATM() {
	};

	/**
	 * Constructor with bank name as string
	 * 
	 * @param bankName
	 */
	public ATM(String bankName) {
		setBankName(bankName);
		setSerialNumber(0);
		setAtmBalance(0.0);
		setDepositsFailed(0);
		setDepositsSuccess(0);
		setDepositTotal(0);
		setTransfersFailed(0);
		setTransfersSuccess(0);
		setTransfersTotal(0);
		setNumberOfTransactions(0);
		setAtmBalance(100.0);
		setLocation("UNKNOWN");
		setWithdrawals(0);
		setWithdrawFailed(0);
		setWithdrawSuccess(0);

		// Initialize the ArrayList
		customers = new ArrayList<Customer>(10);
		// Lets add some customers
		addCustomersToList();
	}

	/**
	 * Constructor with ATM serial number, bank name and location parameters.
	 * 
	 * @param serialNumber
	 *            This is the serial number of the ATM.
	 * @param bankName
	 *            The name of the bank that operates this ATM.
	 * @param location
	 *            The location of the ATM.
	 */
	public ATM(int serialNumber, String bankName, String location) {
		// The serial number of this ATM
		setSerialNumber(serialNumber);
		// Name of bank
		setBankName(bankName);
		// The location of this ATM
		setLocation(location);

		// Set remaining defaults
		setAtmBalance(0.0);
		setDepositsFailed(0);
		setDepositsSuccess(0);
		setDepositTotal(0);
		setTransfersFailed(0);
		setTransfersSuccess(0);
		setTransfersTotal(0);
		setNumberOfTransactions(0);
		setAtmBalance(100.0);
		setWithdrawals(0);
		setWithdrawFailed(0);
		setWithdrawSuccess(0);

		if (customers == null) {
			// Initialize the ArrayList
			customers = new ArrayList<Customer>(10);
		}
		// Lets add some customers
		addCustomersToList();
	}

	//
	// Methods
	//

	private void addCustomersToList() {

		if (customers.isEmpty()) {
			customers.add(new Customer("Alice", 1234, 5000.00)); // 1
			customers.add(new Customer("Tom", 2000, 200.00)); // 2
			customers.add(new Customer("Monica", 3000, 50.00)); // 3
			customers.add(new Customer("Michael", 7777, 0.00)); // 4
			customers.add(new Customer("John", 8000, 500.00)); // 5
			customers.add(new Customer("Jane", 2222, 500.00)); // 6
			customers.add(new Customer("Robert", 2323, 200.00)); // 7
			customers.add(new Customer("Owen", 4455, 50.00)); // 8
			customers.add(new Customer("Chris", 8787, 10.00)); // 9
			customers.add(new Customer("Rebecca", 8080, 555.55)); // 10
		}
	}

	//
	// Accessor methods
	//

	/**
	 * Get the value of customer Holds all of the banks customers names, pin and
	 * balance. This is a static declaration so that the customers are visible
	 * to all the objects of this class.
	 * 
	 * @return the value of customer
	 * @param name
	 *            The name of the customer
	 * @param pin
	 *            The customers account pin
	 */
	private Customer getCustomerFromList(String name, int pin) {

		Customer bankCustomer = new Customer();
		bankCustomer = null;

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName() == name && customers.get(i).getPin() == pin) {
				bankCustomer = customers.get(i);
				// Get out of the loop now that we have a customer
				break;
			}
		}

		return bankCustomer;
	}

	/**
	 * Set the value of atmBalance Holds the current balance of the ATM.
	 * 
	 * @param newVar
	 *            the new value of atmBalance
	 */
	private void setAtmBalance(double newVar) {
		atmBalance = newVar;
	}

	/**
	 * Get the value of atmBalance Holds the current balance of the ATM.
	 * 
	 * @return the value of atmBalance
	 */
	private double getAtmBalance() {
		return atmBalance;
	}

	/**
	 * Set the value of location Holds the physical location of the AMT.
	 * 
	 * @param newVar
	 *            the new value of location
	 */
	private void setLocation(String newVar) {
		location = newVar;
	}

	/**
	 * Get the value of location Holds the physical location of the AMT.
	 * 
	 * @return the value of location
	 */
	private String getLocation() {
		return location;
	}

	/**
	 * Set the value of serialNumber The serial number of this ATM.
	 * 
	 * @param newVar
	 *            the new value of serialNumber
	 */
	private void setSerialNumber(int newVar) {
		serialNumber = newVar;
	}

	/**
	 * Get the value of serialNumber The serial number of this ATM.
	 * 
	 * @return the value of serialNumber
	 */
	private int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Set the value of withdrawals The total number of withdrawals attempted at
	 * the ATM.
	 * 
	 * @param newVar
	 *            the new value of withdrawals
	 */
	private void setWithdrawals(int newVar) {
		withdrawals = newVar;
	}

	/**
	 * Get the value of withdrawals The total number of withdrawals attempted at
	 * the ATM.
	 * 
	 * @return the value of withdrawals
	 */
	private int getWithdrawals() {
		return withdrawals;
	}

	/**
	 * Set the value of withdrawFailed The total number of failed withdrawals at
	 * the ATM.
	 * 
	 * @param newVar
	 *            the new value of withdrawFailed
	 */
	private void setWithdrawFailed(int newVar) {
		withdrawFailed = newVar;
	}

	/**
	 * Get the value of withdrawFailed The total number of failed withdrawals at
	 * the ATM.
	 * 
	 * @return the value of withdrawFailed
	 */
	private int getWithdrawFailed() {
		return withdrawFailed;
	}

	/**
	 * Set the value of withdrawSuccess The total number of successful
	 * withdrawals at the ATM.
	 * 
	 * @param newVar
	 *            the new value of withdrawSuccess
	 */
	private void setWithdrawSuccess(int newVar) {
		withdrawSuccess = newVar;
	}

	/**
	 * Get the value of withdrawSuccess The total number of successful
	 * withdrawals at the ATM.
	 * 
	 * @return the value of withdrawSuccess
	 */
	private int getWithdrawSuccess() {
		return withdrawSuccess;
	}

	/**
	 * Set the value of bankName
	 * 
	 * @param newVar
	 *            the new value of bankName
	 */
	private void setBankName(String newVar) {
		bankName = newVar;
	}

	/**
	 * Get the value of bankName
	 * 
	 * @return the value of bankName
	 */
	private String getBankName() {
		return bankName;
	}

	/**
	 * Set the value of numberOfTransactions The total number of transactions at
	 * this ATM
	 * 
	 * @param newVar
	 *            the new value of numberOfTransactions
	 */
	private void setNumberOfTransactions(int newVar) {
		numberOfTransactions = newVar;
	}

	/**
	 * Get the value of numberOfTransactions The total number of transactions at
	 * this ATM
	 * 
	 * @return the value of numberOfTransactions
	 */
	private int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	/**
	 * Set the value of depositTotal The total number of deposits at this ATM,
	 * succeeded and failed
	 * 
	 * @param newVar
	 *            the new value of depositTotal
	 */
	private void setDepositTotal(int newVar) {
		depositTotal = newVar;
	}

	/**
	 * Get the value of depositTotal The total number of deposits at this ATM,
	 * succeeded and failed
	 * 
	 * @return the value of depositTotal
	 */
	private int getDepositTotal() {
		return depositTotal;
	}

	/**
	 * Set the value of depositsFailed The total number of deposits that failed
	 * 
	 * @param newVar
	 *            the new value of depositsFailed
	 */
	private void setDepositsFailed(int newVar) {
		depositsFailed = newVar;
	}

	/**
	 * Get the value of depositsFailed The total number of deposits that failed
	 * 
	 * @return the value of depositsFailed
	 */
	private int getDepositsFailed() {
		return depositsFailed;
	}

	/**
	 * Set the value of depositsSuccess The total number of deposits that
	 * succeeded
	 * 
	 * @param newVar
	 *            the new value of depositsSuccess
	 */
	private void setDepositsSuccess(int newVar) {
		depositsSuccess = newVar;
	}

	/**
	 * Get the value of depositsSuccess The total number of deposits that
	 * succeeded
	 * 
	 * @return the value of depositsSuccess
	 */
	private int getDepositsSuccess() {
		return depositsSuccess;
	}

	/**
	 * Set the value of transfersFailed The number of transfers at this ATM that
	 * failed.
	 * 
	 * @param newVar
	 *            the new value of transfersFailed
	 */
	private void setTransfersFailed(int newVar) {
		transfersFailed = newVar;
	}

	/**
	 * Get the value of transfersFailed The number of transfers at this ATM that
	 * failed.
	 * 
	 * @return the value of transfersFailed
	 */
	private int getTransfersFailed() {
		return transfersFailed;
	}

	/**
	 * Set the value of transfersSuccess The number of transfers at this ATM
	 * that succeeded
	 * 
	 * @param newVar
	 *            the new value of transfersSuccess
	 */
	private void setTransfersSuccess(int newVar) {
		transfersSuccess = newVar;
	}

	/**
	 * Get the value of transfersSuccess The number of transfers at this ATM
	 * that succeeded
	 * 
	 * @return the value of transfersSuccess
	 */
	private int getTransfersSuccess() {
		return transfersSuccess;
	}

	/**
	 * Set the value of transfersTotal The total number of transfers at this
	 * ATM, succeeded and failed
	 * 
	 * @param newVar
	 *            the new value of transfersTotal
	 */
	private void setTransfersTotal(int newVar) {
		transfersTotal = newVar;
	}

	/**
	 * Get the value of transfersTotal The total number of transfers at this
	 * ATM, succeeded and failed
	 * 
	 * @return the value of transfersTotal
	 */
	private int getTransfersTotal() {
		return transfersTotal;
	}

	//
	// Other methods
	//

	/**
	 * Adds an amount to the ATM's balance.
	 * 
	 * @param amount
	 *            The amount of funds to add to the ATMs' balance.
	 */
	public void addFund(double amount) {
		
		if(amount > 0){
			// Adds physical money to ATM from the bank
			setAtmBalance(getAtmBalance() + amount);
		}else{
			System.out.println("Failure - Adding ATM funds: " +
		this.getLocation() + " " + this.getSerialNumber() + "\n");
		}
	}

	/**
	 * This checks to see if the ATM object pass to this instance is the same or
	 * equal. If the object passed to this instance, the method returns true,
	 * otherwise false. Global attributes are compared to evaluate this
	 * condition.
	 * 
	 * @return boolean
	 * @param compareATMObject
	 *            The ATM object with which to compare.
	 */
	public boolean equals(ATM compareATMObject) {
		// Check the vital attributes to see if the objects are the same ATM

		boolean isEqual;

		isEqual = (this.getLocation() == compareATMObject.getLocation()
				&& this.getSerialNumber() == compareATMObject.getSerialNumber()
				&& this.getBankName() == compareATMObject.getBankName()
				&& this.getAtmBalance() == compareATMObject.getAtmBalance());

		return isEqual;
	}

	/**
	 * Allows customer to make a deposit when they provide their name, PIN and
	 * amount to deposit.
	 * 
	 * @return boolean
	 * @param customerName
	 *            The customers first name.
	 * @param customerPin
	 *            The customers PIN number.
	 * @param amount
	 *            The amount to deposit into customer account.
	 */
	public boolean deposit(String customerName, int customerPin, double amount) {

		boolean isDepositSuccess = false;

		// Update the transaction log
		setNumberOfTransactions(getNumberOfTransactions() + 1);
		// Update the number of deposits
		setDepositTotal(getDepositTotal() + 1);
		
		// Get ready for deposit message
		String strMessage = "";


		// Create a customer object
		Customer currentCustomer = new Customer();
		// Get the customer information from the list
		currentCustomer = getCustomerFromList(customerName, customerPin);

		//Check to see that we have a customer and the amount is greater than zero 
		if (currentCustomer != null && amount > 0) {
			// Add the deposit to the balance of the customer account
			currentCustomer.setBalance(currentCustomer.getBalance() + amount);

			// Update the customer account list
			if (updateCustomerList(currentCustomer)) {
				setDepositsSuccess(getDepositsSuccess() + 1);
				isDepositSuccess = true;

				// Format success message
				strMessage = String
						.format("Succeed – deposit: %s new balance: $%1.2f",
								currentCustomer.getName(),
								currentCustomer.getBalance());
			} else {
				setDepositsFailed(getDepositsFailed() + 1);
				// Format failure message
				strMessage = String
						.format("Failure – deposit: %s balance: $%1.2f",
								currentCustomer.getName(),
								currentCustomer.getBalance());
			}
		} else {
			setDepositsFailed(getDepositsFailed() + 1);
			// Format failure message
			strMessage = String.format("Failure – deposit: %s", customerName);
		}

		// Send confirmation success or failure message
		System.out.println(strMessage);
		
		return isDepositSuccess;
	}

	/**
	 * Displays the menu for the ATM users screen.
	 */
	public void displayMenu() {
		// Display the ATM menu
		System.out.println("===== ATM Transaction Menu =====");
		System.out.println("      1.  Withdrawal");
		System.out.println("      2.  Deposit");
		System.out.println("      2.  Transfer");

	}

	/**
	 * Sets the serial number and location of ATM.
	 * 
	 * @param serialNumber
	 *            The serial number of the ATM.
	 * @param location
	 *            The location of the ATM.
	 */
	public void setATM(int serialNumber, String location) {
		// Set the ATM serial number
		this.serialNumber = serialNumber;
		// Set the location of this ATM
		this.location = location;
	}

	/**
	 * Prints the status of the ATM object
	 */
	public void status() {

		String str;
		str = getNumberOfTransactions() + " Transactions so far:" + "\n";
		str += "    Withdawal: " + getWithdrawals() + " ("
				+ getWithdrawSuccess() + " success, " + getWithdrawFailed()
				+ " fail)\n";
		str += "    Deposit: " + getDepositTotal() + " ("
				+ getDepositsSuccess() + " success, " + getDepositsFailed()
				+ " fail)\n";
		str += "    Tranfer: " + getTransfersTotal() + " ("
				+ getTransfersSuccess() + " success, " + getTransfersFailed()
				+ " fail)\n";

		System.out.println(toString());
		System.out.println(str);
	}

	/**
	 * Prints the AMT information to the screen. Invoked by native classes.
	 * 
	 * @return String
	 */
	public String toString() {
		String str;
		str = "  Serial Number: " + getSerialNumber() + "\n";
		str += "  Bank Name: " + getBankName() + "\n";
		str += "  Location: " + getLocation() + "\n";
		str += "  Balance: " + String.format("$%1.2f", getAtmBalance()) + "\n";

		return str;
	}

	/**
	 * Transfers funds from a bank customer to another bank customer.
	 * 
	 * @return boolean
	 * @param fromCustomerName
	 *            The customers name that is transferring money from the
	 *            account.
	 * @param fromPinNumber
	 *            The pin number of the customer transferring funds.
	 * @param transferAmount
	 *            The amount of money to transfer.
	 * @param toCustomerName
	 *            The name of the customer receiving transfered funds.
	 * @param toPinNumber
	 *            The PIN number of the customer that is receiving the
	 *            transfered funds.
	 */
	public boolean transfer(String fromCustomerName, int fromPinNumber,
			double transferAmount, String toCustomerName, int toPinNumber) {
		boolean isTransferComplete = false;

		// Update the transaction log
		setNumberOfTransactions(getNumberOfTransactions() + 1);
		setTransfersTotal(getTransfersTotal() + 1);

		// Create two customer objects to hold the transfer from and to
		Customer transferFromCustomer = new Customer();
		Customer transferToCustomer = new Customer();

		// Lets get the customers information from the list
		transferFromCustomer = getCustomerFromList(fromCustomerName,fromPinNumber);
		transferToCustomer = getCustomerFromList(toCustomerName, toPinNumber);
		
		String strTransferMessage;
		
		if (transferFromCustomer != null && transferToCustomer != null
				&& transferAmount > 0) {

			// Subtract from the balance
			transferFromCustomer.setBalance(transferFromCustomer.getBalance()
					- transferAmount);
			// Add to balance of this customer
			transferToCustomer.setBalance(transferToCustomer.getBalance()
					+ transferAmount);

			/*
			 * Now we need to update with success We should update the from
			 * account first, this keeps the bank in the positive if we can't
			 * update to account
			 */
			if (updateCustomerList(transferFromCustomer)) {
				// Now update the transfer to account
				if (updateCustomerList(transferToCustomer)) {
					// Set the success flag
					isTransferComplete = true;
				} else {
					// Roll back transfer from account
					transferFromCustomer.setBalance(transferFromCustomer
							.getBalance() + transferAmount);
					updateCustomerList(transferFromCustomer);
				}
			}

			if (isTransferComplete) {
				// Update transfer success counter
				setTransfersSuccess(getTransfersSuccess() + 1);
				// Format message
				strTransferMessage = String
						.format("Succeed – transfer: %s new balance: $%1.2f, %s new balance: $%1.2f",
								transferFromCustomer.getName(),
								transferFromCustomer.getBalance(),
								transferToCustomer.getName(),
								transferToCustomer.getBalance());
			} else {
				setTransfersFailed(getTransfersFailed() + 1);
				// Format message
				strTransferMessage = String
						.format("Failure – transfer: %s balance: $%1.2f, %s balance: $%1.2f",
								transferFromCustomer.getName(),
								transferFromCustomer.getBalance(),
								transferToCustomer.getName(),
								transferToCustomer.getBalance());
			}
		} else {
			strTransferMessage = "Transfer - Failure";
		}
		System.out.println(strTransferMessage);

		return isTransferComplete;
	}

	/**
	 * Updates the customers list with the latest information of a customer
	 * after a transaction
	 * 
	 * @return boolean
	 * @param customerUpdate
	 *            Holds the updated customer information used to update the
	 *            customer list
	 */
	public boolean updateCustomerList(Customer customerUpdate) {
		// Create the flag for final result
		boolean isUpdatedSuccess = false;
		// Search for the customer to update
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName() == customerUpdate.getName()
					&& customers.get(i).getPin() == customerUpdate.getPin()) {
				// Set the updated customer information in the list
				customers.set(customers.indexOf(customerUpdate), customerUpdate);
				// Set the flag for success
				isUpdatedSuccess = true;
				// Get out of the loop now that we have a customer
				break;
			}
		}
		return isUpdatedSuccess;
	}

	/**
	 * Withdraws funds from a bank customers account if funds are available.
	 * 
	 * @return boolean
	 * @param name
	 *            Customer name
	 * @param pin
	 *            The account pin
	 * @param amount
	 *            The amount of funds to withdraw
	 */
	public boolean withdrawal(String name, int pin, double amount) {

		boolean isSuccessfull = false;

		// Add to the total number of transactions
		setNumberOfTransactions(getNumberOfTransactions() + 1);
		// Add to the total number of withdrawals
		setWithdrawals(getWithdrawals() + 1);
		// Create a new object to hold the customer
		Customer currentCustomer = new Customer();
		// Try and get the customer from the list
		currentCustomer = getCustomerFromList(name, pin);
		
		if (currentCustomer != null && amount > 0) {
			
			if (amount <= getAtmBalance() && amount <= currentCustomer.getBalance()) {
				// Debt the customers account
				currentCustomer.setBalance(currentCustomer.getBalance() - amount);
				// Update the customer account list
				isSuccessfull = updateCustomerList(currentCustomer);

				if (isSuccessfull) {
					// Set the new AMT balance
					setAtmBalance(getAtmBalance() - amount);
					// Add to the total number of successful withdrawals
					setWithdrawSuccess(getWithdrawSuccess() + 1);
					// Print success message
					System.out.printf("\nSucceed – withdrawal: %s new balance: $%1.2f\n",
							currentCustomer.getName(), currentCustomer.getBalance());
				} else {
					withdrawalFail();
				}

			} else {
				withdrawalFail();
			}
		} else {
			withdrawalFail();
		}

		return isSuccessfull;
	}

	/**
	 * If the withdrawal attempt is a failure this method updates the withdrawal
	 * failure count and sends a withdrawal failed message to the user
	 */
	private void withdrawalFail() {
		// Add to the total number of failed withdrawals
		setWithdrawFailed(getWithdrawFailed() + 1);
		// Print fail message
		System.out.println("\nFail – withdrawal");
	}

}
