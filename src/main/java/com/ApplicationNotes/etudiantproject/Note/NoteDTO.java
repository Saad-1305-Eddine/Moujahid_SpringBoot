package com.ApplicationNotes.etudiantproject.Note;

public class NoteDTO {

    private int note;
    private Long matiereID;
    private Long etudiantID;


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Long getMatiereID() {
        return matiereID;
    }

    public void setMatiereID(Long matiereID) {
        this.matiereID = matiereID;
    }

    public Long getEtudiantID() {
        return etudiantID;
    }

    public void setEtudiantID(Long etudiantID) {
        this.etudiantID = etudiantID;
    }
}
