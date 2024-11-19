package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"") // Usa comillas dobles para escapar la palabra reservada
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Figure> figures = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // Nueva colección polimórfica
    @Transient // No se persiste en la base de datos
    private List<Collectible> collectibles = new ArrayList<>();

    public List<Collectible> getCollectibles() {
        return collectibles;
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
        figure.setUser(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Método para agregar cualquier tipo de Collectible
    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);

        if (collectible instanceof Figure) {
            figures.add((Figure) collectible); // Persistencia
            ((Figure) collectible).setUser(this);
        } else if (collectible instanceof Card) {
            cards.add((Card) collectible); // Persistencia
            ((Card) collectible).setUser(this);
        }
    }

    // Método para mostrar todos los Collectible
    public void showCollectibles() {
        collectibles.forEach(c -> System.out.println(c.getDetailedDescription()));
    }

    // Getters y setters omitidos por brevedad
}
