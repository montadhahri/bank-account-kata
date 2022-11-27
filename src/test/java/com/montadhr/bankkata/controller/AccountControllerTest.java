package com.montadhr.bankkata.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.montadhr.bankkata.entities.Account;
import com.montadhr.bankkata.entities.Client;
import com.montadhr.bankkata.exceptions.NotFoundException;
import com.montadhr.bankkata.services.IAccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class )
public class AccountControllerTest {
	private Client client = new Client("test", "test");

    @MockBean
    private IAccountService accountService;
    
    @Autowired
    private MockMvc mockMvc;

    @Test
	@DisplayName("Test create account Success")
    public void TestCreateAccountSuccess() throws Exception {
        Account account = new Account(client, 100d);

        when(accountService.create(any()))
                .thenReturn(account);

        String json = "{\"firstName\":\"test\",\"lastName\":\"test\"}";
        this.mockMvc.perform(post("/api/accounts/").header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
	@DisplayName("Test get account Success")
    public void TestGetAccountSuccess() throws Exception {
        Account account = new Account(client, 100d);

        when(accountService.getAccount(any())).thenReturn(account);

        this.mockMvc.perform(get("/api/accounts/" + account.getAccountNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
	@DisplayName("Test get account not found")
    public void TestGetAccountNotFound() throws Exception {

    	when(accountService.getAccount(any())).thenThrow(NotFoundException.class);

        this.mockMvc.perform(get("/api/accounts/1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
