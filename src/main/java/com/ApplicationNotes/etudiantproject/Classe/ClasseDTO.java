package com.ApplicationNotes.etudiantproject.Classe;

import com.ApplicationNotes.etudiantproject.Filiere.Filiere;
import jakarta.persistence.*;

public class ClasseDTO {

    private String nom;
    private int niveau;
    private Long filiereID;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Long getFiliereID() {
        return filiereID;
    }

    public void setFiliereID(Long filiereID) {
        this.filiereID = filiereID;
    }
}
