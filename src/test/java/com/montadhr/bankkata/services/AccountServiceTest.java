package com.montadhr.bankkata.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.exceptions.NotFoundException;
import com.montadhr.bankkata.services.impl.AccountService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AccountService.class })
public class AccountServiceTest {
	private Client client = new Client("test", "test");

	@Autowired
	private IAccountService accountService;

	@Test
	@DisplayName("Test create account Success")
	public void TestCreateSuccess() {
		Account createdAccount = accountService.create(client);
		assertNotNull(createdAccount);
		assertNotNull(createdAccount.getAccountNumber());
		assertEquals(createdAccount.getBalance(), 0d);
	}

	@Test()
	@DisplayName("Test get account by AccountNumber Success")
	public void TestGetAccountSuccess() {
		Account createdAccount = accountService.create(client);

		Account insertedAccount = accountService.getAccount(createdAccount.getAccountNumber());
		assertNotNull(insertedAccount);
		assertEquals(insertedAccount, createdAccount);
	}

	@Test()
	@DisplayName("Test get account by AccountNumber Not found")
	public void TestGetAccountNotFoundFailed() {
		assertThrows(NotFoundException.class, () -> {
			accountService.getAccount("1254");
		}, "Account with number: 1254 not found");
	}
}
