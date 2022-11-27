package com.montadhr.bankkata.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.montadhr.bankkata.entities.Operation;
import com.montadhr.bankkata.enums.OperationType;
import com.montadhr.bankkata.exceptions.NotAllowedException;
import com.montadhr.bankkata.exceptions.NotFoundException;
import com.montadhr.bankkata.services.IOperationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OperationController.class)
public class OperationControllerTest {
	private static final String BODY_REQUEST_JSON = "{\"accountNumber\":\"123456\",\"amount\":50}";

	@MockBean
	private IOperationService operationService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test get operations history success")
	public void TestGetOperationsHistorySuccess() throws Exception {
		List<Operation> operations = new ArrayList<>();
		operations.add(new Operation(OperationType.DEPOSIT, 50d));
		operations.add(new Operation(OperationType.DEPOSIT, 100d));

		when(operationService.getOperationsHistory(any())).thenReturn(operations);

		this.mockMvc.perform(get("/api/operations/1234").header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test deposit success")
	public void TestDepositSuccess() throws Exception {
		Operation operation = new Operation(OperationType.DEPOSIT, 50d);

		when(operationService.deposit(any())).thenReturn(operation);

		this.mockMvc.perform(post("/api/operations/deposit")
				.content(BODY_REQUEST_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test deposit Account Not Found failed")
	public void TestDepositAccountNotFound() throws Exception {
		when(operationService.deposit(any())).thenThrow(NotFoundException.class);

		this.mockMvc.perform(post("/api/operations/deposit")
				.content(BODY_REQUEST_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Test deposit Negative Amount failed")
	public void TestDepositNegativeAmountFailed() throws Exception {
		when(operationService.deposit(any())).thenThrow(NotAllowedException.class);

		this.mockMvc.perform(post("/api/operations/deposit")
				.content(BODY_REQUEST_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Test withdrawal success")
	public void TestWithdrawalSuccess() throws Exception {
		Operation operation = new Operation(OperationType.WITHDRAWAL, 50d);

		when(operationService.deposit(any())).thenReturn(operation);

		this.mockMvc.perform(post("/api/operations/withdrawal")
				.content(BODY_REQUEST_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
