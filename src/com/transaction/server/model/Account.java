package com.transaction.server.model;
// TODO: Auto-generated Javadoc

/**
 * The Class Account stands for account objects in db.
 */
public class Account {

	/** The account number. */
	String accountNumber;
	
	/** The account holder. */
	String accountHolder;
	
	/** The balance. */
	double balance;
	
	/**
	 * Instantiates a new account.
	 *
	 * @param accountNumber the account number
	 * @param accountHolder the account holder
	 * @param balance the balance
	 */
	public Account(String accountNumber, String accountHolder, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
	}
	
	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * Gets the account holder.
	 *
	 * @return the account holder
	 */
	public String getAccountHolder() {
		return accountHolder;
	}
	
	/**
	 * Sets the account holder.
	 *
	 * @param accountHolder the new account holder
	 */
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
