package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.CardMapper;
import com.m2bar.Banking.System.models.CardDto;
import com.m2bar.Banking.System.repository.CardRepository;
import com.m2bar.Banking.System.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CardDto createCard(CardDto card) {
        return CardMapper.CARD_MAPPER.entityToDto(
                cardRepository.save(
                        CardMapper.CARD_MAPPER.dtoToEntity(
                                card
                        )
                )
        );
    }

    @Override
    public CardDto getCardId(Long cardId) {
        return CardMapper.CARD_MAPPER.entityToDto(
                cardRepository.findById(cardId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Card with id %d was not found", cardId)
                                )
                        )
        );
    }

    @Override
    public List<CardDto> getAllCards() {
        return CardMapper.CARD_MAPPER.entityToDto(
                cardRepository.findAll()
        );
    }

    @Override
    public List<CardDto> getAllCardsByCustomerId(Long customerId) {
        return CardMapper.CARD_MAPPER.entityToDto(
                cardRepository.findByCustomerId(customerId)
        );
    }
}
