package com.montadhr.bankkata.entities;

import java.time.LocalDate;

import com.montadhr.bankkata.enums.OperationType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Operation {

	private OperationType type;

	private double amount;

	private LocalDate date;

	public Operation(OperationType type, double amount) {
		super();
		this.type = type;
		this.amount = amount;
		this.date = LocalDate.now();
	}
}
