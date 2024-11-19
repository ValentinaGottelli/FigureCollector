package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Card extends Collectible implements Tradable{
    private String rarity;

    public Card() {
    }

    public Card(String name, String series, String rarity, int releaseYear) {
        super(name, series, releaseYear);
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    // Implementación del método abstracto
    @Override
    public String displaySummary() {
        return String.format("Card: %s [%s] - Rarity: %s, Released in %d",
                getName(), getSeries(), rarity, getReleaseYear());
    }

    @Override
    public String getDetailedDescription() {
        return String.format("Card: %s [%s] - Rarity: %s", getName(), getSeries(), rarity);
    }


    @Override
    public double calculateTradeValue() {
        return switch (rarity.toLowerCase()) {
            case "common" -> 5.0;
            case "rare" -> 20.0;
            case "legendary" -> 50.0;
            default -> 10.0;
        };
    }

    @Override
    public boolean isEligibleForTrade() {
        return !"common".equalsIgnoreCase(rarity);
    }

    @Override
    public String getTradeDescription() {
        return String.format("Card: %s [%s] - Rarity: %s, Trade Value: $%.2f",
                getName(), getSeries(), rarity, calculateTradeValue());
    }

    @Override
    public boolean isVintage(int currentYear) {
        return currentYear - getReleaseYear() > 20;
    }
}