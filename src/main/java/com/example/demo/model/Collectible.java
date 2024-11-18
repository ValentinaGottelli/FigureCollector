package com.example.demo.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Collectible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String series;
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Collectible() {
    }

    public Collectible(String name, String series, int releaseYear) {
        this.name = name;
        this.series = series;
        this.releaseYear = releaseYear;
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

    public abstract String getDetailedDescription();

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public abstract boolean isVintage(int currentYear);
}