package com.example.demo;

import com.example.demo.model.*;

public class Main {
    public static void main(String[] args) {
        // Crear un usuario
        User user = new User("Mady");

        // Crear figuras y cartas
        Figure figure = new Figure("Superman", "DC", "Action", 2000);
        Card card = new Card("Pikachu", "Pokemon", "Rare", 1998);

        // Agregar coleccionables al usuario
        user.addCollectible(figure);
        user.addCollectible(card);

        // Mostrar la colección
        System.out.println("Colección de " + user.getName() + ":");
        user.showCollectibles();

        // Mostrar resumen de cada coleccionable
        System.out.println("\nResumen de cada coleccionable:");
        System.out.println(figure.displaySummary());
        System.out.println(card.displaySummary());

        // Intentar agregar un duplicado (debería fallar)
        try {
            user.addCollectible(figure); // Agregar duplicado
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
