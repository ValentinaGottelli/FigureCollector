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

    public Figure(String name, String series, String category) {
        super(name, series);
        this.category = category;
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


}