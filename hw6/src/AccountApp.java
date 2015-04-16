/***************************************************************************************
 *                         hw6/AccountApp.java - Copyright rciampa
 *                         
 * Title: Homework #6 M
 * 
 * Abstract: A simple banking system that uses interfaces, interface implementations
 * 			 and inheritance. The System allows the user to enter the account balances
 * 			 , fees and an interest rate. The user can then deposit or withdraw from
 * 			 the accounts by using a simple option entry selection and then quit the
 * 			 system to get the final account balances
 *
 * @author rciampa
 * ID: 7470
 * Date: Wed April 1 2015 : 20:13:03
 * File: hw6/AccountApp.java
 * Requires: CheckingAccount.java, SavingsAccount.java
 *
 * This application created for the CST338 course at CSU Monterey Bay SP:2015 Semester.
 * This software is not licensed or warranted for any other purpose than the CSUMB
 * scholastic 2015 semester with Dr. Byun.
 *
 * Heading.java
 * /home/rciampa/Documents/Umbrello/headings/heading.java
 *
 ***************************************************************************************/

import java.text.NumberFormat;
import java.util.Scanner;


public class AccountApp {
	
	public static void main(String args[]) {
		//Create a scanner object
		Scanner kb = new Scanner(System.in);
		NumberFormat local = NumberFormat.getCurrencyInstance();
		double checkBalance, savBalance, fee, rate, amount;
		int counter = 0;
		String accType = "", tranType = "";
		
		
		// Asks user to input initial values 
		System.out.println("Welcome to the Account application\n");
		//Get the opening checking balance 
		checkBalance = getInitialCheckingBalance(kb);
		//Get the opening savings balance 
		savBalance = getInitalSavingsBalance(kb);
		//Get the checking account fee
		fee = getCheckingFee(kb);
		//Get the savings interest rate
		rate = getSavingsInterestRate(kb);
		
		CheckingAccount chkAcc = new CheckingAccount(checkBalance,fee);
		SavingsAccount savAcc = new SavingsAccount(savBalance,rate);
		
		//Write user information to console
		System.out.println("\nOK! This is your information");
		System.out.println("Checking Amount: " + local.format(checkBalance));
		System.out.println("Savings Amount:  " + local.format(savBalance));
		System.out.println("Checking Fee:  " + local.format(fee));
		System.out.printf("Interest Rate:  %1.0f%%\n",(rate * 100));
		
		//Enter Transactions for the month
		System.out.println("\nEnter the transactions for the month");
		
		do {
			System.out.print("\nWithdrawal or deposit? (w/d): ");
			tranType = kb.next();
			System.out.print("Checking or savings? (c/s): ");
			accType = kb.next();
			System.out.print("Amount?: ");
			amount = kb.nextDouble();
			
			if(accType.equalsIgnoreCase("c") && 
					tranType.equalsIgnoreCase("d")) {
				chkAcc.deposit(amount);
				counter++;
			} else if (accType.equalsIgnoreCase("s") &&
					tranType.equalsIgnoreCase("d")){
				savAcc.deposit(amount);
			} else if (accType.equalsIgnoreCase("c") &&
					tranType.equalsIgnoreCase("w")) {
				chkAcc.withdraw(amount);
				counter++;
			} else if (accType.equalsIgnoreCase("s") &&
					tranType.equalsIgnoreCase("w")) {
				savAcc.withdraw(amount);
			}
			
			System.out.print("\nContinue? (y/n): ");
		} while(kb.next().equalsIgnoreCase("Y"));
		
		//Payments and fees
		System.out.println("Monthly Payments and Fees");
		System.out.printf("Checking fee:              $%1.2f\n",
				(chkAcc.chargeFee(counter) * counter));
		System.out.printf("Savings interest payment:  $%1.2f\n",
				savAcc.getInterestPayment());
		
		//Make interest payment
		savAcc.setInterestPayment();
		System.out.println();
		
		//Print to console the final balances post transactions
		System.out.println("Final Balances");
		System.out.printf("Checking:  %1.2f\n", chkAcc.getBalance());
		System.out.printf("Savings:   %1.2f\n", savAcc.getBalance());
	}
	
	/**
	 * Request the initial checking account balance from user
	 * 
	 * @param kb A configured scanner object
	 * @return The opening balance
	 */
	private static double getInitialCheckingBalance(Scanner kb){
		System.out.print("Enter initial checking amount: ");
		return kb.nextDouble();
	}
	
	/**
	 * Request the initial savings account balance from user
	 * 
	 * @param kb A configured scanner object
	 * @return The opening balance
	 */
	private static double getInitalSavingsBalance(Scanner kb){
		System.out.print("Enter initial saving amount: ");
		return kb.nextDouble();
	}
	
	/**
	 * Request the checking schedule fee from user
	 * 
	 * @param kb A configured scanner object
	 * @return Checking fee
	 */
	private static double getCheckingFee(Scanner kb){
		System.out.print("Enter checking fee: ");
		return kb.nextDouble();
	}
	
	/**
	 * Request the interest rate from user
	 * 
	 * @param kb A configured scanner object
	 * @return The interest rate
	 */
	private static double getSavingsInterestRate(Scanner kb){
		System.out.print("Enter Saving interest: ");
		return kb.nextDouble();
	}
}
