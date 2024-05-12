package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.Branch;
import com.m2bar.Banking.System.models.BranchDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper BRANCH_MAPPER = Mappers.getMapper(BranchMapper.class);

    BranchDto entityToDto(Branch entity);

    List<BranchDto> entityToDto(List<Branch> entity);

    Branch dtoToEntity(BranchDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget Branch target, Branch source);
}
