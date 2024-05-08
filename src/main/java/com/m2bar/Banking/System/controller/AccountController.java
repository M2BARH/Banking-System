package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.AccountsApi;
import com.m2bar.Banking.System.models.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements AccountsApi {
    @Override
    public ResponseEntity<Void> accountsAccountIdDelete(Long accountId) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> accountsAccountIdGet(Long accountId) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> accountsAccountIdPut(Long accountId, AccountDto body) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> accountsAccountNumberGet(Long accountNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountDto>> accountsCustomerIdGet(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountDto>> accountsGet() {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> accountsPost(AccountDto body) {
        return null;
    }
}
