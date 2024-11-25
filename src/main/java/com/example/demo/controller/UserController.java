package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Figure;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/figures")
    public ResponseEntity<String> addFigureToUser(@PathVariable Long userId, @RequestBody Figure figure) {
        return ResponseEntity.ok(userService.addFigureToUser(userId, figure));
    }

    @PostMapping("/{userId}/cards")
    public ResponseEntity<String> addFigureToUser(@PathVariable Long userId, @RequestBody Card card) {
        return ResponseEntity.ok(userService.addCardToUser(userId, card));
    }

    @PostMapping("/{userId}/figures/{figureId}/review")
    public ResponseEntity<Review> addReview(@PathVariable Long userId, @PathVariable Long figureId, @RequestBody int rating) {
        return ResponseEntity.ok(userService.addReview(userId, figureId, rating));
    }
}