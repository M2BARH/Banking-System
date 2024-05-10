package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto createTransaction(TransactionDto transaction);

    TransactionDto getTransactionById(Long transactionId);

    List<TransactionDto> getAllTransactionsByAccountId(Long accountId);
}
