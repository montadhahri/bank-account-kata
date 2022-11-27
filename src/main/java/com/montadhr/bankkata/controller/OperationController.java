package com.montadhr.bankkata.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montadhr.bankkata.dto.OperationDto;
import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.services.IOperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/operations")
@Api(value = "Restfull APIs for Operations")
public class OperationController {

	@Autowired
	private IOperationService operationService;

	@GetMapping("history/{accountNumber}")
	@ApiOperation(value = "Get history (operation, date, amount, balance) of my operations")
	@ApiResponses(
			value = { 
				@ApiResponse(responseCode = "200", description = "The request has succeeded"),
				@ApiResponse(responseCode = "404", description = "Account with number not found") 
			})
	public List<Operation> getOperationsHistory(@PathVariable("accountNumber") String accountNumber) {
		return operationService.getOperationsHistory(accountNumber);
	}

	@PostMapping("deposit")
	@ApiOperation(value = "Deposit in specific account")
	@ApiResponses(
			value = { 
				@ApiResponse(responseCode = "200", description = "The request has succeeded"),
				@ApiResponse(responseCode = "400", description = "Cannot deposit negative amount of money"),
				@ApiResponse(responseCode = "404", description = "Account with number not found")
			})
	public Operation deposit(@Valid @RequestBody OperationDto dto) {
		return operationService.deposit(dto);
	}

	@PostMapping("withdrawal")
	@ApiOperation(value = "Withdrawal from specific account")
	@ApiResponses(
			value = { 
				@ApiResponse(responseCode = "200", description = "The request has succeeded"),
				@ApiResponse(responseCode = "400", description = "Cannot withdrawal negative amount of money"),
				@ApiResponse(responseCode = "400", description = "Account balance insufficient"),
				@ApiResponse(responseCode = "404", description = "Account with number not found")
			})
	public Operation withdrawal(@Valid @RequestBody OperationDto dto) {
		return operationService.withdrawal(dto);
	}
}
