package com.code.croissant.controllers;

import com.code.croissant.model.LoginRequest;
import com.code.croissant.model.LoginResponse;
import com.code.croissant.model.Donnateur;
import com.code.croissant.repositories.DonnateurRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final DonnateurRepository repository;

    // Injection via constructeur
    public AuthController(DonnateurRepository repository) {
        this.repository = repository;
    }

    // Endpoint login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return repository.findByEmail(request.getEmail())
            .map(d -> {
                if (d.getPassword().equals(request.getPassword())) {
                    return new LoginResponse(true, "Connexion réussie ✅");
                } else {
                    return new LoginResponse(false, "Mot de passe incorrect ❌");
                }
            })
            .orElseGet(() -> new LoginResponse(false, "Email inconnu ❌"));
    }


//Quand le front envoie l’email et le mot de passe à :
// Le serveur :
// cherche l’utilisateur par email
// vérifie le mot de passe
// répond :
//✅ Connexion réussie si c’est correct
//❌ Mot de passe incorrect ou Email inconnu sinon


    // Endpoint register
    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> register(@RequestBody Donnateur donnateur) {
        Map<String, Boolean> response = new HashMap<>();

        // Vérifier si l'email existe déjà
        if (repository.findByEmail(donnateur.getEmail()).isPresent()) {
            response.put("success", false);
            return ResponseEntity.ok(response);
        }

        // Enregistrer le nouvel utilisateur
        repository.save(donnateur);
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
}

//Le serveur :
//vérifie si l’email existe déjà
//s’il existe → échec
//sinon → enregistre l’utilisateur dans la base