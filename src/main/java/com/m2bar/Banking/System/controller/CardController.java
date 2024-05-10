package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.CardsApi;
import com.m2bar.Banking.System.models.CardDto;
import com.m2bar.Banking.System.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CardController implements CardsApi {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public ResponseEntity<CardDto> getCardById(Long cardId) {
        try {
            return new ResponseEntity<>(cardService.getCardId(cardId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve card");
        }
    }

    @Override
    public ResponseEntity<List<CardDto>> getAllCardsByCustomerId(Long customerId) {
        try {
            return new ResponseEntity<>(cardService.getAllCardsByCustomerId(customerId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve card", ex);
        }
    }

    @Override
    public ResponseEntity<List<CardDto>> getAllCards() {
        try {
            return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve cards", ex);
        }
    }

    @Override
    public ResponseEntity<CardDto> createCard(CardDto body) {
        try {
            cardService.createCard(body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create card", ex);
        }
    }
}