package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/figures/{figureId}")
    public ResponseEntity<String> postFigureForSale(@PathVariable Long userId, @PathVariable Long figureId, @RequestBody double price) {
        return ResponseEntity.ok(userService.postFigureForSale(userId, figureId, price));
    }
}