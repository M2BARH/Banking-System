package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.TransactionsApi;
import com.m2bar.Banking.System.models.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements TransactionsApi {
    @Override
    public ResponseEntity<List<TransactionDto>> transactionsGet(Long accountId) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> transactionsPost(Long accountId, TransactionDto body) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionDto> transactionsTransactionIdGet(Long transactionId) {
        return null;
    }
}
