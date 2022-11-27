package com.montadhr.bankkata.services;

import java.util.List;

import com.montadhr.bankkata.dto.OperationDto;
import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.exceptions.NotFoundException;

public interface IOperationService {

	/**
	 * Statement printing
	 * 
	 * @param accountNumber
	 * @return list of operations
	 * @exception NotFoundException: Account not found
	 */
	List<Operation> getOperationsHistory(String accountNumber);

	Operation deposit(OperationDto dto);

	Operation withdrawal(OperationDto dto);
}
