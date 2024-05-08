package com.m2bar.Banking.System.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    
    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);
}
