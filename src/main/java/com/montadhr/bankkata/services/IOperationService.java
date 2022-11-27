package com.montadhr.bankkata.services;

import java.util.List;

import com.montadhr.bankkata.dto.OperationDto;
import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.exceptions.NotAllowedException;
import com.montadhr.bankkata.exceptions.NotFoundException;


/**
 * Handle Business Account actions
 * @author mdh
 *
 */
public interface IOperationService {

	/**
	 * Statement printing
	 * 
	 * @param accountNumber
	 * @return list of operations
	 * @exception NotFoundException: Account not found
	 */
	List<Operation> getOperationsHistory(String accountNumber);

	/**
	 * Deposit amout of money in account
	 * 
	 * @param dto
	 * @return Operation: created deposit Operation
	 * @exception NotAllowedException: Cannot withdrawal negative amount of money
	 * @exception NotFoundException:   Account not found
	 */
	Operation deposit(OperationDto dto);

	/**
	 * 
	 * @param dto
	 * @return Operation: created withdrawal Operation
	 * @exception NotAllowedException: Cannot withdrawal negative amount of money
	 * @exception NotFoundException:   Account not found
	 */
	Operation withdrawal(OperationDto dto);
}
