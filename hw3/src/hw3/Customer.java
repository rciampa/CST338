package hw3;


/**
 * Class Customer
 */
public class Customer {

  //
  // Fields
  //

  /**
   * Customer name
   */
  private String name;
  /**
   * Customer ATM pin
   */
  private int pin;
  /**
   * Customer account balance
   */
  private double balance;
  
  //
  // Constructors
  //
  public Customer() {}
  
  public Customer (String name, int pin, double balance) {
	  
	  setName(name);
	  setBalance(balance);
	  setPin(pin);
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of name
   * Customer name
   * @param newVar the new value of name
   */
  public void setName ( String newVar ) {
    name = newVar;
  }

  /**
   * Get the value of name
   * Customer name
   * @return the value of name
   */
  public String getName ( ) {
    return name;
  }

  /**
   * Set the value of pin
   * Customer ATM pin
   * @param newVar the new value of pin
   */
  public void setPin ( int newVar ) {
    pin = newVar;
  }

  /**
   * Get the value of pin
   * Customer ATM pin
   * @return the value of pin
   */
  public int getPin ( ) {
    return pin;
  }

  /**
   * Set the value of balance
   * Customer account balance
   * @param newVar the new value of balance
   */
  public void setBalance ( double newVar ) {
    balance = newVar;
  }

  /**
   * Get the value of balance
   * Customer account balance
   * @return the value of balance
   */
  public double getBalance ( ) {
    return balance;
  }
}
