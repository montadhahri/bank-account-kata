package com.montadhr.bankkata.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montadhr.bankkata.dto.OperationDto;
import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.enums.OperationType;
import com.montadhr.bankkata.exceptions.NotFoundException;
import com.montadhr.bankkata.services.IAccountService;
import com.montadhr.bankkata.services.IOperationService;
import com.montadhr.bankkata.exceptions.NotAllowedException;

/**
 * Handle Business Account actions
 * 
 * @author mdh
 *
 */
@Service
public class OperationService implements IOperationService {

	@Autowired
	private IAccountService accountService;

	/**
	 * Statement printing
	 * 
	 * @param accountNumber
	 * @return list of operations
	 * @exception NotFoundException: Account not found
	 */
	@Override
	public List<Operation> getOperationsHistory(String accountNumber) {
		Account account = accountService.getAccount(accountNumber);
		return account.getOperations();
	}

	/**
	 * Deposit amout of money in account
	 * 
	 * @param dto
	 * @return Operation: created deposit Operation
	 * @exception NotAllowedException: Cannot withdrawal negative amount of money
     * @exception NotFoundException: Account not found
	 */
	@Override
	public Operation deposit(OperationDto dto) {
		if (dto.getAmount() < 0) {
			throw new NotAllowedException("Cannot deposit negative amount of money");
		}

		Account account = accountService.getAccount(dto.getAccountNumber());

		Operation newOperation = new Operation(OperationType.DEPOSIT, dto.getAmount());

		double balance = account.getBalance() + dto.getAmount();
		account.setBalance(balance);
		account.setOperation(newOperation);
		return newOperation;
	}

	/**
	 * 
	 * @param dto
	 * @return Operation: created withdrawal Operation
	 * @exception NotAllowedException: Cannot withdrawal negative amount of money
     * @exception NotFoundException: Account not found
	 */
	@Override
	public Operation withdrawal(OperationDto dto) {
		if (dto.getAmount() < 0) {
			throw new NotAllowedException("Cannot withdrawal negative amount of money");
		}
		Account account = accountService.getAccount(dto.getAccountNumber());
		if (dto.getAmount() > account.getBalance()) {
			throw new NotAllowedException("Account balance insufficient");
		}

		Operation newOperation = new Operation(OperationType.WITHDRAWAL, dto.getAmount());

		double balance = account.getBalance() - dto.getAmount();
		account.setBalance(balance);
		account.setOperation(newOperation);

		return newOperation;
	}
}
