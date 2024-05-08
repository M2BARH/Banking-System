package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.CardsApi;
import com.m2bar.Banking.System.models.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController implements CardsApi {
    @Override
    public ResponseEntity<List<CardDto>> cardsCardIdGet(Long cardId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CardDto>> cardsGet(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<CardDto> cardsPost(Long customerId, CardDto body) {
        return null;
    }
}
