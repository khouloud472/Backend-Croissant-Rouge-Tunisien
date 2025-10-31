package com.code.croissant.repositories;

import com.code.croissant.model.Donnateur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonnateurRepository extends JpaRepository<Donnateur, Long> {
}

