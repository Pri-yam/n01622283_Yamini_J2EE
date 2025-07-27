package com.example.coffeeshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with all arguments
@AllArgsConstructor // Generates a no-args constructor
@Entity
@Table(name = "coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate IDs
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private double price;

    private String description;
}
