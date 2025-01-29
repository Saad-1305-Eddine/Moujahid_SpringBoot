package com.ApplicationNotes.etudiantproject.Filiere;


import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiere")
public class FiliereController {

    private FiliereService filiereService;

    @Autowired
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @PostMapping("add")
    public Filiere add(@RequestBody Filiere filiere) {
        return filiereService.add(filiere);
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        filiereService.deleteByIdFiliere(id);
        return "Filiere deleted successfully with ID: " + id;
    }

    @GetMapping("find/{id}")
    public Filiere findById(@PathVariable Long id) {
        return filiereService.getFiliereById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found with id " + id));
    }


    @PutMapping("update/{id}")
    public Filiere update(@RequestBody Filiere filiere, @PathVariable Long id) {
        if (!filiere.getFiliereID().equals(id)) {
            throw new IllegalArgumentException("ID in the request body does not match ID in the path");
        }
        return filiereService.update(filiere);
    }


    @GetMapping("getAll")
    public List<Filiere> getFiliers() {
        return filiereService.getFilieres();
    }
}
