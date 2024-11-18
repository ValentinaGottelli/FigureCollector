package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FigureRepository figureRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CardRepository cardRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public String addFigureToUser(Long userId, Figure figure) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        boolean hasFigure = user.getFigures().stream()
                .anyMatch(f -> f.getName().equals(figure.getName()) && f.getSeries().equals(figure.getSeries()));

        if (hasFigure) {
            return "User already has this figure in their collection.";
        }

        figure.setUser(user);
        Figure savedFigure = figureRepository.save(figure);

        user.getFigures().add(savedFigure);
        userRepository.save(user);

        return "Figure added to user's collection.";
    }


    public Review addReview(Long userId, Long figureId, int rating) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Figure figure = figureRepository.findById(figureId).orElseThrow(() -> new RuntimeException("Figure not found"));

        Review review = new Review(rating, user, figure);
        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findByFigure(figure);
        figure.updateAverageRating(reviews);
        figureRepository.save(figure);

        return review;
    }

    public String postFigureForSale(Long userId, Long figureId, double price) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Figure figure = figureRepository.findById(figureId).orElseThrow(() -> new RuntimeException("Figure not found"));

        boolean hasFigure = user.getFigures().contains(figure);

        if (!hasFigure) {
            return "User does not have this figure in their collection.";
        }

        Sale sale = new Sale(price, user, figure);
        saleRepository.save(sale);

        return "Figure posted for sale.";
    }

    public String addCardToUser(Long userId, Card card) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        boolean hasCard = user.getCards().stream()
                .anyMatch(c -> c.getName().equals(card.getName()) && c.getSeries().equals(card.getSeries()));

        if (hasCard) {
            return "User already has this card in their collection.";
        }

        card.setUser(user);
        Card savedCard = cardRepository.save(card);

        user.getCards().add(savedCard);
        userRepository.save(user);

        return "Card added to user's collection.";
    }
}