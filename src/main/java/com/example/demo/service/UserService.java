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
        // we should build a decorator for this or pre-handler
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            // ho can we be sure that the user does not exist?
            throw new RuntimeException("User not found");
        }
    }

    public String addFigureToUser(Long userId, Figure figure) {
        // we should not check in the service layer whether the user exists or not
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // we could have a transaction in here since we're making two changes to the database
        figure.setUser(user);
        Figure savedFigure = figureRepository.save(figure);

        user.getFigures().add(savedFigure);
        userRepository.save(user);

        // we should return the figure object
        return "Figure added to user's collection.";
    }



    public String postFigureForSale(Long userId, Long figureId, double price) {
        // same thing here, we should build a pre-handler decorator whatever serice layer should 
        // not be responsible for checking if the user or figure exists.
        // also authentication to know if the user is the owner of the figure
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Figure figure = figureRepository.findById(figureId).orElseThrow(() -> new RuntimeException("Figure not found"));

        boolean hasFigure = user.getFigures().contains(figure);

        if (!hasFigure) {
            return "User does not have this figure in their collection.";
        }

        Sale sale = new Sale(price, user, figure);
        saleRepository.save(sale);

        return "Figure posted for sale."; // let's make the response more informative
        // or 204 no content or we return the sale object
    }

    public String addCardToUser(Long userId, Card card) {
        // same thing here, we should build a pre-handler decorator whatever serice layer should
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        card.setUser(user);
        Card savedCard = cardRepository.save(card);

        user.getCards().add(savedCard);
        userRepository.save(user);

        // we should return the card object
        return "Card added to user's collection.";
    }
}