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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/accounts")
@Api(value = "Restfull APIs for Accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping("{accountNumber}")
	@ApiOperation(value = "Get Account details (balance) by accountNumber")
	@ApiResponses(
			value = { 
				@ApiResponse(responseCode = "200", description = "The request has succeeded"),
				@ApiResponse(responseCode = "404", description = "Account with number not found") 
			})
	public Account getOne(@PathVariable("accountNumber") String accountNumber) {
		return accountService.getAccount(accountNumber);
	}

	@PostMapping("")
	@ApiOperation(value = "Create new Account for specific Client")
	@ApiResponses(
			value = { 
				@ApiResponse(responseCode = "200", description = "The request has succeeded")
			})
	public Account create(@RequestBody Client client) {
		return accountService.create(client);
	}
}
