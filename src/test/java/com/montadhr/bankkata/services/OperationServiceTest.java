package com.montadhr.bankkata.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.montadhr.bankkata.dto.OperationDto;
import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.enums.OperationType;
import com.montadhr.bankkata.exceptions.NotAllowedException;
import com.montadhr.bankkata.services.impl.OperationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { OperationService.class })
public class OperationServiceTest {
	private Client client = new Client("test", "test");


	@Autowired
	private IOperationService operationService;

	@MockBean
	private IAccountService accountService;

	@Test
	@DisplayName("Test get Operations History Success")
	public void TestGetOperationsHistory_success() {
		Account account = new Account(client, 100d);
		account.setOperation(new Operation(OperationType.DEPOSIT, 50d));
		account.setOperation(new Operation(OperationType.DEPOSIT, 100d));
		account.setOperation(new Operation(OperationType.WITHDRAWAL, 40d));

		when(accountService.getAccount(any())).thenReturn(account);

		List<Operation> operationsHistory = operationService.getOperationsHistory(account.getAccountNumber());
		assertNotNull(operationsHistory);
		assertEquals(operationsHistory.size(), 3);
		assertEquals(operationsHistory.get(0).getAmount(), 50d);
		assertEquals(operationsHistory.get(1).getAmount(), 100d);
		assertEquals(operationsHistory.get(2).getAmount(), 40d);
	}

	@Test
	@DisplayName("Test deposit amount Success")
	public void TestDepositSuccess() {
		Account account = new Account(client, 100d);
		Operation expectedOperation = new Operation(OperationType.DEPOSIT, 50d);
 
		when(accountService.getAccount(any())).thenReturn(account);

		Operation insertedOperation = operationService.deposit(new OperationDto(account.getAccountNumber(), 50d));
		assertEquals(insertedOperation, expectedOperation);
		assertEquals(account.getBalance(), 150d);
	}

	@Test
	@DisplayName("Test deposit negative amount failed")
	public void TestDepositNegativeAmountFailed() {
		Account account = new Account(client, 100d);
		when(accountService.getAccount(any())).thenReturn(account);
		
		assertThrows(NotAllowedException.class, () -> {
			operationService.deposit(new OperationDto("1254", -50d));
		}, "Cannot withdrawal negative amount of money");
	}

	@Test
	@DisplayName("Test withdrawal success")
	public void TestWithdrawalSuccess() {
		Account account = new Account(client, 100d);
		Operation expectedOperation = new Operation(OperationType.WITHDRAWAL, 50d);
 
		when(accountService.getAccount(any())).thenReturn(account);

		Operation insertedOperation = operationService.withdrawal(new OperationDto(account.getAccountNumber(), 50d));
		assertEquals(insertedOperation, expectedOperation);
		assertEquals(account.getBalance(), 50d);
	}

	@Test
	@DisplayName("Test withdrawal negative amount failed")
	public void TestWithdrawalNegativeAmountFailed() {
		Account account = new Account(client, 100d);

		when(accountService.getAccount(any())).thenReturn(account);

		assertThrows(NotAllowedException.class, () -> {
			operationService.withdrawal(new OperationDto(account.getAccountNumber(), -50d));
		}, "Cannot withdrawal negative amount of money");
	}

	@Test
	@DisplayName("Test withdrawal account balance insufficient failed")
	public void TestWithdrawalAccountBalanceInsufficientFailed() {
		Account account = new Account(client, 100d);

		when(accountService.getAccount(any())).thenReturn(account);

		assertThrows(NotAllowedException.class, () -> {
			operationService.withdrawal(new OperationDto(account.getAccountNumber(), 250d));
		}, "Account balance insufficient");
	}
}
