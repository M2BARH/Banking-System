package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.AccountsApi;
import com.m2bar.Banking.System.models.AccountDto;
import com.m2bar.Banking.System.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AccountController implements AccountsApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<Void> deleteAccountById(Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete account", ex);
        }
    }

    @Override
    public ResponseEntity<AccountDto> getAccountById(Long accountId) {
        try {
            return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve account", ex);
        }
    }

    @Override
    public ResponseEntity<AccountDto> updateAccountById(Long accountId, AccountDto body) {
        try {
            return new ResponseEntity<>(accountService.updateAccount(accountId, body), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update account", ex);
        }
    }

    @Override
    public ResponseEntity<AccountDto> getAccountByAccountNumber(String accountNumber) {
        try {
            return new ResponseEntity<>(accountService.getAccountByAccountNumber(accountNumber), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve account by account number", ex);
        }
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAllAccountsByCustomerId(Long customerId) {
        try {
            return new ResponseEntity<>(accountService.getAllAccountsByCustomerId(customerId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve account by customer Id", ex);
        }
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        try {
            return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve accounts", ex);
        }
    }

    @Override
    public ResponseEntity<AccountDto> createAccount(AccountDto body) {
        try {
            return new ResponseEntity<>(accountService.createAccount(body), HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create account", ex);
        }
    }
}
