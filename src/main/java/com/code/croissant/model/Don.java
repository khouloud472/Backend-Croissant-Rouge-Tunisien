package com.code.croissant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private LocalDate dateDon;
    private String typeDon;
    private String description;

    @ManyToOne
    @JoinColumn(name = "donnateur_id")
    private Donnateur donateur;

    @ManyToOne
    private Element element;

}
