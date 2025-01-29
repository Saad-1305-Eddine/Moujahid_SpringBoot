package com.ApplicationNotes.etudiantproject.Note.NotesManagement;

import java.util.List;

public class EtudiantEnRattrapage {

    private Long etudiantID;
    private String nom;
    private String prenom;
    private Long classeID;
    private String classeNom;
    private List<MatiereNote> matieresEnRattrapage; // Liste des matières en rattrapage



    public EtudiantEnRattrapage(Long etudiantID, String nom, String prenom, Long classeID, String classeNom, List<MatiereNote> matieresEnRattrapage) {
        this.etudiantID = etudiantID;
        this.nom = nom;
        this.prenom = prenom;
        this.classeID = classeID;
        this.classeNom = classeNom;
        this.matieresEnRattrapage = matieresEnRattrapage;
    }

    // Getters
    public Long getEtudiantID() {
        return etudiantID;
    }

    public String getNom() {
        return nom;
    }

    public Long getClasseID() {
        return classeID;
    }

    public String getClasseNom() {
        return classeNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<MatiereNote> getMatieresEnRattrapage() {
        return matieresEnRattrapage;
    }


    public static class MatiereNote {
        private String matiereNom;
        private double note;

        public MatiereNote(String matiereNom, double note) {
            this.matiereNom = matiereNom;
            this.note = note;
        }

        public String getMatiereNom() {
            return matiereNom;
        }

        public double getNote() {
            return note;
        }
    }
}
