package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Figure extends Collectible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String category;
    private double averageRating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Figure() {
    }

    public Figure(String name, String series, String category, int releaseYear) {
        super(name, series, releaseYear);
        this.category = category;
    }

    @Override
    public boolean isVintage(int currentYear) {
        return currentYear - getReleaseYear() > 15;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void updateAverageRating(List<Review> reviews) {
        this.averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public String getDetailedDescription() {
        return String.format("Figure: %s [%s] - Category: %s, Average Rating: %.1f",
                getName(), getSeries(), category, averageRating);
    }
}