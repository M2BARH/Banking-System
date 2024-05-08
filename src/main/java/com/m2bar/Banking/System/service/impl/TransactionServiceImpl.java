package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.TransactionDto;
import com.m2bar.Banking.System.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public TransactionDto createTransaction(TransactionDto transaction) {
        return null;
    }

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactionsByAccountId(Long accountId) {
        return null;
    }
}
