package com.m2bar.Banking.System.mapper;

import com.m2bar.Banking.System.entity.Card;
import com.m2bar.Banking.System.models.CardDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardMapper CARD_MAPPER = Mappers.getMapper(CardMapper.class);

    CardDto entityToDto(Card entity);

    List<CardDto> entityToDto(List<Card> entity);

    Card dtoToEntity(CardDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget Card target, Card source);
}
