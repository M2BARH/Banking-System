package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.TransactionMapper;
import com.m2bar.Banking.System.models.TransactionDto;
import com.m2bar.Banking.System.repository.TransactionRepository;
import com.m2bar.Banking.System.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transaction) {
        return TransactionMapper.TRANSACTION_MAPPER.entityToDto(
                transactionRepository.save(
                        TransactionMapper.TRANSACTION_MAPPER.dtoToEntity(
                                transaction
                        )
                )
        );
    }

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        return TransactionMapper.TRANSACTION_MAPPER.entityToDto(
                transactionRepository.findById(transactionId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Transaction with id %d was not found", transactionId)
                                )
                        )
        );
    }

    @Override
    public List<TransactionDto> getAllTransactionsByAccountId(Long accountId) {
        return TransactionMapper.TRANSACTION_MAPPER.entityToDto(
                transactionRepository.findByAccountId(accountId)
        );
    }
}
