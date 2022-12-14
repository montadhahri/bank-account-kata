package com.montadhr.bankkata.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.exceptions.NotFoundException;
import com.montadhr.bankkata.services.IAccountService;

/**
 * Handle Business Account actions
 * @author mdh
 *
 */
@Service
public class AccountService implements IAccountService {
    private Map<String, Account> accounts = new HashMap<>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Account create(Client client) {
    	Account account =  new Account(client);
    	accounts.put(account.getAccountNumber(), account);
    	return account;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Account getAccount(String accountNumber) {
    	if(!accounts.containsKey(accountNumber)) {
    		throw new NotFoundException("Account with number" + accountNumber +" not found");
    	}
    	return accounts.get(accountNumber);
    }
}
