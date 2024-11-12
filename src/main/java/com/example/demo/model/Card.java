package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Card extends Collectible {
    private String rarity;

    public Card() {
    }

    public Card(String name, String series, String rarity) {
        super(name, series);
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}