package com.ApplicationNotes.etudiantproject.Filiere;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filiereID;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getFiliereID() {
        return filiereID;
    }

    public void setFiliereID(Long filiereID) {
        this.filiereID = filiereID;
    }
}
