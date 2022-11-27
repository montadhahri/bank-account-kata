package com.montadhr.bankkata.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Account {

	private String accountNumber;

	private Client client;

	private double balance;
	
	@JsonIgnore
	private List<Operation> operations; 

	public Account(Client client) {
		super();
		this.accountNumber = UUID.randomUUID().toString();
		this.client = client;
		this.balance = 0d;
		this.operations = new ArrayList<>();
	}

	public Account(Client client, double balance) {
		super();
		this.accountNumber = UUID.randomUUID().toString();
		this.client = client;
		this.balance = balance;
		this.operations = new ArrayList<>();
	}
	
	public void setOperation(Operation operation) {
		this.operations.add(operation);
	}
}
