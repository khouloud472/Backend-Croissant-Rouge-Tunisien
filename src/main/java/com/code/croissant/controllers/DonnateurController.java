package com.code.croissant.controllers;

import com.code.croissant.model.Donnateur;
import com.code.croissant.model.Don;

import com.code.croissant.model.Element;
import com.code.croissant.repositories.DonRepository;
import com.code.croissant.repositories.DonnateurRepository;
import com.code.croissant.repositories.ElementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // to allow Angular access
@RestController
@RequestMapping("/api/donnateurs/{donateurId}")
public class DonnateurController {

    @Autowired
    private DonnateurRepository donnateurRepository;
    @Autowired
    private DonRepository donRepository;
    @Autowired
    private ElementRepository elementRepository;


    //voir les elements possibles
    @GetMapping("")
    public List<Element> getElement() {
        return elementRepository.findAll();
    }

    // Voir tous les dons d’un donateur
    @GetMapping("/dons")
    public List<Don> getDonsByDonateur(@PathVariable Long donateurId) {
        return donRepository.findByDonateur_Id(donateurId);
    }

    // 🔴 Supprimer un don
    @DeleteMapping("/dons/{donId}")
    public void deleteDon(@PathVariable Long donId) {
        donRepository.deleteById(donId);
    }

    // Faire un don pour un élément
    @PostMapping("/dons")
    public Don createDon(
            @PathVariable Long donateurId,
            @RequestParam Long elementId,
            @RequestBody Don donRequest) { //donRequest est un json d'apres le front

        // Vérifier que le donateur existe
        Donnateur donnateur = donnateurRepository.findById(donateurId)
                .orElseThrow(() -> new RuntimeException("Donnateur non trouvé avec ID : " + donateurId));

        //  Vérifier que l’élément existe
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new RuntimeException("Élément non trouvé avec ID : " + elementId));

        // Lier les entités
        donRequest.setDonateur(donnateur);
        donRequest.setElement(element);

        //  Ajouter la date du don automatiquement
        donRequest.setDateDon(LocalDate.now());

        //  Sauvegarder le don
        return donRepository.save(donRequest);
    }


}
