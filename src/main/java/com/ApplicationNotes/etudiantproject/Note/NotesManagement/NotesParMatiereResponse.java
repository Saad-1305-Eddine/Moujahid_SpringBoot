package com.ApplicationNotes.etudiantproject.Note.NotesManagement;

import java.util.List;

public class NotesParMatiereResponse {

    private Long matiereID;
    private List<EtudiantNote> etudiantNotes;

    public NotesParMatiereResponse(Long matiereID, List<EtudiantNote> etudiantNotes) {
        this.matiereID = matiereID;
        this.etudiantNotes = etudiantNotes;
    }

    public Long getMatiereID() {
        return matiereID;
    }

    public void setMatiereID(Long matiereID) {
        this.matiereID = matiereID;
    }

    public List<EtudiantNote> getEtudiantNotes() {
        return etudiantNotes;
    }

    public void setEtudiantNotes(List<EtudiantNote> etudiantNotes) {
        this.etudiantNotes = etudiantNotes;
    }
}

class EtudiantNote {
    private Long etudiantID;
    private int note;

    public EtudiantNote(Long etudiantID, int note) {
        this.etudiantID = etudiantID;
        this.note = note;
    }

    public Long getEtudiantID() {
        return etudiantID;
    }

    public void setEtudiantID(Long etudiantID) {
        this.etudiantID = etudiantID;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
