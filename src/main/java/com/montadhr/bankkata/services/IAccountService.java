package com.montadhr.bankkata.services;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.exceptions.NotFoundException;

/**
 * Handle Business Account actions
 * 
 * @author mdh
 *
 */
public interface IAccountService {

	/**
	 * Create new Account for specific Client
	 * 
	 * @param client
	 * @return
	 */
	Account create(Client client);

	/**
	 * Get account by accountNumber
	 * 
	 * @param accountNumber: number of account (like RIB)
	 * @return created Account
	 * @exception NotFoundException: Account not found
	 */
	Account getAccount(String accountNumber);
}
