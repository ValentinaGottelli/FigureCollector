package com.example.demo.controller;

import com.example.demo.model.Figure;
import com.example.demo.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/figures")
// we shouldnt pluralize the endpoint
public class FigureController {
    @Autowired
    private FigureRepository figureRepository;

    @GetMapping
    public List<Figure> getAllFigures() {
        return figureRepository.findAll();
    }

    @PostMapping
    public Figure createFigure(@RequestBody Figure figure) {
        return figureRepository.save(figure);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Figure> getFigureById(@PathVariable(value = "id") Long id) {
        return figureRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFigure(@PathVariable(value = "id") Long id) {
        if (figureRepository.existsById(id)) {
            figureRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}