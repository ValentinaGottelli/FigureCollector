package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
        Optional<Card> existingCard = cardRepository.findById(id);

        if (existingCard.isPresent()) {
            Card card = existingCard.get();
            card.setName(updatedCard.getName());
            card.setSeries(updatedCard.getSeries());
            card.setRarity(updatedCard.getRarity());
            card.setReleaseYear(updatedCard.getReleaseYear());
            cardRepository.save(card);
            return ResponseEntity.ok(card);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tradable")
    public ResponseEntity<List<Card>> getTradableCards() {
        List<Card> cards = cardRepository.findAll();
        List<Card> tradableCards = cards.stream()
                .filter(Card::isEligibleForTrade)
                .toList();
        return ResponseEntity.ok(tradableCards);
    }

    @GetMapping("/{id}/trade-description")
    public ResponseEntity<String> getTradeDescription(@PathVariable Long id) {
        Optional<Card> card = cardRepository.findById(id);

        if (card.isPresent()) {
            String tradeDescription = card.get().getTradeDescription();
            return ResponseEntity.ok(tradeDescription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
