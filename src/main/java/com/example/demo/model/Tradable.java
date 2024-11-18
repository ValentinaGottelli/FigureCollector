package com.example.demo.model;

public interface Tradable {
    double calculateTradeValue();
    boolean isEligibleForTrade();
    String getTradeDescription();
}
