package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.Account;
import com.m2bar.Banking.System.models.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);

    AccountDto entityToDto(Account entity);

    List<AccountDto> entityToDto(List<Account> entity);

    Account dtoToEntity(AccountDto dto);

    void update(@MappingTarget Account target, Account source);
}
