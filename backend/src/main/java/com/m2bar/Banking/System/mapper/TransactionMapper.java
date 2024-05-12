package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.Transaction;
import com.m2bar.Banking.System.models.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper TRANSACTION_MAPPER = Mappers.getMapper(TransactionMapper.class);

    TransactionDto entityToDto(Transaction entity);

    List<TransactionDto> entityToDto(List<Transaction> entity);

    Transaction dtoToEntity(TransactionDto dto);
}
