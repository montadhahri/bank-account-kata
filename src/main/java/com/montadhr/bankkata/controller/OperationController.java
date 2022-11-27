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

@RestController
@RequestMapping("api/operations")
public class OperationController {

	@Autowired
	private IOperationService operationService;

	@GetMapping("history/{accountNumber}")
	public List<Operation> getOperationsHistory(@PathVariable("accountNumber") String accountNumber) {
		return operationService.getOperationsHistory(accountNumber);
	}

	@PostMapping("deposit")
	public Operation deposit(@Valid @RequestBody OperationDto dto) {
		return operationService.deposit(dto);
	}

	@PostMapping("withdrawal")
	public Operation withdrawal(@Valid @RequestBody OperationDto dto) {
		return operationService.withdrawal(dto);
	}
}
