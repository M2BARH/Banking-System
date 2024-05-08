package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.CardDto;

import java.util.List;

public interface CardService {

    CardDto createCard(CardDto card);

    List<CardDto> getAllCardsByCustomerId(Long customerId);
}
