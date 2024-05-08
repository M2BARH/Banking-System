package com.m2bar.Banking.System.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper TRANSACTION_MAPPER = Mappers.getMapper(TransactionMapper.class);
}
