package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.CardDto;
import com.m2bar.Banking.System.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public CardDto createCard(CardDto card) {
        return null;
    }

    @Override
    public List<CardDto> getAllCardsByCustomerId(Long customerId) {
        return null;
    }
}
