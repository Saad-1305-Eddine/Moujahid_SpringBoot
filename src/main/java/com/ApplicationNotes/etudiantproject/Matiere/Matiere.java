package com.ApplicationNotes.etudiantproject.Matiere;

import com.ApplicationNotes.etudiantproject.Classe.Classe;
import jakarta.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matiereID;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "classeID")
    private Classe classe;

    public Long getMatiereID() {
        return matiereID;
    }

    public void setMatiereID(Long matiereID) {
        this.matiereID = matiereID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
