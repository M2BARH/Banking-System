package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.AccountMapper;
import com.m2bar.Banking.System.models.AccountDto;
import com.m2bar.Banking.System.repository.AccountRepository;
import com.m2bar.Banking.System.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto account) {
        return AccountMapper.ACCOUNT_MAPPER.entityToDto(
                accountRepository.save(
                        AccountMapper.ACCOUNT_MAPPER.dtoToEntity(account)
                )
        );
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        return AccountMapper.ACCOUNT_MAPPER.entityToDto(
                accountRepository.findById(accountId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Account with id %d not found", accountId)
                                )
                        )
        );
    }

    @Override
    public AccountDto getAccountByAccountNumber(String accountNumber) {
        return AccountMapper.ACCOUNT_MAPPER.entityToDto(
                accountRepository.findByAccountNumber(accountNumber)
        );
    }

    @Override
    public List<AccountDto> getAllAccountsByCustomerId(Long customerId) {
        return AccountMapper.ACCOUNT_MAPPER.entityToDto(
                accountRepository.findAllByCustomerId(customerId)
        );
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return AccountMapper.ACCOUNT_MAPPER.entityToDto(
                accountRepository.findAll()
        );
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto account) {
        AccountDto existingAccount = getAccountById(accountId);
        AccountMapper.ACCOUNT_MAPPER.update(
                AccountMapper.ACCOUNT_MAPPER.dtoToEntity(existingAccount),
                AccountMapper.ACCOUNT_MAPPER.dtoToEntity(account)
        );
        return createAccount(existingAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.delete(
                AccountMapper.ACCOUNT_MAPPER.dtoToEntity(
                        getAccountById(accountId)
                )
        );
    }
}
