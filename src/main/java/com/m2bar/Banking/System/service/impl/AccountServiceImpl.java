package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.AccountDto;
import com.m2bar.Banking.System.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDto createAccount(AccountDto account) {
        return null;
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        return null;
    }

    @Override
    public AccountDto getAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccountsByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return null;
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto account) {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {

    }
}
