package com.ApplicationNotes.etudiantproject.Classe;
import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Filiere.Filiere;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classe")
public class Classe {

    //Module CLASSE :( CRUD=>(ID,NOM,NIVEAU(int) ,FILIERE (ID) )

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classeID;

    private String nom;
    private int niveau;
    @ManyToOne
    @JoinColumn(name = "filiereID")
    private Filiere filiere;

    public Long getClasseID() {
        return classeID;
    }

    public void setClasseID(Long classeID) {
        this.classeID = classeID;
    }

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

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

}
