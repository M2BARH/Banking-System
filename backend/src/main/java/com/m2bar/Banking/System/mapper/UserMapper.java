package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.User;
import com.m2bar.Banking.System.models.UserDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper CUSTOMER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(User entity);

    List<UserDto> entityToDto(List<User> entity);

    User dtoToEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget User target, User source);
}
