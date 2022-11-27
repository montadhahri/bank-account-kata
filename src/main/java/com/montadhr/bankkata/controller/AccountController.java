package com.montadhr.bankkata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.services.IAccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping("{accountNumber}")
	public Account getOne(@PathVariable("accountNumber") String accountNumber) {
		return accountService.getAccount(accountNumber);
	}

	@PostMapping("")
	public Account create(@RequestBody Client client) {
		return accountService.create(client);
	}
}
