package com.m2bar.Banking.System.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper BRANCH_MAPPER = Mappers.getMapper(BranchMapper.class);
}
