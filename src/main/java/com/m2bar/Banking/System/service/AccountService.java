package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long accountId);

    AccountDto getAccountByAccountNumber(String accountNumber);

    List<AccountDto> getAllAccountsByCustomerId(Long customerId);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(Long accountId, AccountDto account);

    void deleteAccount(Long accountId);
}
