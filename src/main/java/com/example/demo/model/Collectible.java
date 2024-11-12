package com.example.demo.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Collectible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String series;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Collectible() {
    }

    public Collectible(String name, String series) {
        this.name = name;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setUser(User user) {
        this.user = user;
    }
}