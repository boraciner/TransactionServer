package com.transaction.server.repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.transaction.server.model.Account;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionRepoImpl.
 */
public class TransactionRepoImpl implements TransactionRepo {

	/** The account DB, we could use Standard HashMap because of using synchronized block in transferMoney*/
	static ConcurrentHashMap<String, Account> accountDB = new ConcurrentHashMap<String, Account>();
	
	{
		//Add new accounts to DB
		accountDB.put("user1", new Account("0532013001", "user1", 1000d));
		accountDB.put("user2", new Account("0532013002", "user2", 2000d));
		accountDB.put("user3", new Account("0532013003", "user3", 3000d));
	}
	
	/**
	 * Transfer money.
	 *
	 * @param from the from
	 * @param to the to
	 * @param amount the amount
	 * @return true, if two accounts are present and balance is enough then transfer is successful 
	 */
	@Override
	public synchronized boolean transferMoney(String from, String to, double amount) {
		
		Optional<Account> accountFrom = getAccount(from);
		Optional<Account> accountTo = getAccount(to);
		if(accountFrom.isPresent() && accountTo.isPresent() && accountFrom.get().getBalance()>=amount) {
			accountTo.get().setBalance(accountTo.get().getBalance()+amount);
			accountFrom.get().setBalance(accountFrom.get().getBalance()-amount);
			return true;
		}
		return false;
	}

	/**
	 * Gets the account.
	 *
	 * @param accountHolder the account holder
	 * @return the account
	 */
	@Override
	public Optional<Account> getAccount(String accountHolder) {
		if(accountDB.containsKey(accountHolder))
			return Optional.of(accountDB.get(accountHolder));
		else
			return Optional.empty();
	}

}
