package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.Address;
import com.m2bar.Banking.System.models.AddressDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    AddressMapper ADDRESS_MAPPER = Mappers.getMapper(AddressMapper.class);

    AddressDto entityToDto(Address entity);

    List<AddressDto> entityToDto(List<Address> entity);

    Address dtoToEntity(AddressDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget Address target, Address source);
}
