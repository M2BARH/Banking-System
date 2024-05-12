package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.TransactionsApi;
import com.m2bar.Banking.System.models.TransactionDto;
import com.m2bar.Banking.System.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TransactionController implements TransactionsApi {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByAccountId(Long accountId) {
        try {
            return new ResponseEntity<>(transactionService.getAllTransactionsByAccountId(accountId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve transactions", ex);
        }
    }

    @Override
    public ResponseEntity<TransactionDto> createTransaction(TransactionDto body) {
        try {
            transactionService.createTransaction(body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create transactions", ex);
        }
    }

    @Override
    public ResponseEntity<TransactionDto> getTransactionById(Long transactionId) {
        try {
            return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve transaction", ex);
        }
    }
}
