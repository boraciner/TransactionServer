package com.transaction.server.repository;

import java.util.Optional;

import com.transaction.server.model.Account;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionRepo is an abstraction layer from Database.
 */
public interface TransactionRepo {

	/**
	 * Transfer money.
	 *
	 * @param from the from
	 * @param to the to
	 * @param amount the amount
	 * @return true, if successful
	 */
	boolean transferMoney(String from ,String to, double amount);
	
	/**
	 * Gets the account.
	 *
	 * @param accountHolder the account holder
	 * @return the account
	 */
	Optional<Account> getAccount(String accountHolder);
	
}
