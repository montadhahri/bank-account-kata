package com.montadhr.bankkata.services;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;

public interface IAccountService {

	Account create(Client client);
	
	Account getAccount(String accountNumber);
}
