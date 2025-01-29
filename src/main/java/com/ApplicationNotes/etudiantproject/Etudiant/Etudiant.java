package com.ApplicationNotes.etudiantproject.Etudiant;

import com.ApplicationNotes.etudiantproject.Classe.Classe;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "etudiant")
public class Etudiant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long etudiantID;
        private String nom;
        private String prenom;
        private LocalDate dateNaissance;

        @ManyToOne
        @JoinColumn(name = "classeID")
        private Classe classe;

        public Long getEtudiantID() { return etudiantID; }

        public void setEtudiantID(Long etudiantID) { this.etudiantID = etudiantID; }

        public String getNom() { return nom; }

        public void setNom(String nom) { this.nom = nom; }

        public String getPrenom() { return prenom; }

        public void setPrenom(String prenom) { this.prenom = prenom; }

        public LocalDate getDateNaissance() { return dateNaissance; }

        public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

        public Classe getClasse() { return classe; }

        public void setClasse(Classe classe) { this.classe = classe;}

}
