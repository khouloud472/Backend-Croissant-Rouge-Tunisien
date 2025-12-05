package com.code.croissant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String image;
    private String label;
}


/*
Ce code représente un élément que l’on peut donner (dans la base de données).

✅ Ce que contient un élément :
id → numéro unique de l’élément
description → description de l’élément
image → image de l’élément
label → nom ou titre de l’élément

✅ À quoi sert ce fichier :
Il crée la table Element dans la base de données
Il sert à afficher les choses qu’on peut donner dans l’application
Il est utilisé quand un utilisateur crée un don

✅ En une phrase très simple :
Cette classe décrit un objet que l’on peut donner. ✅
*/