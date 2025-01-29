package com.ApplicationNotes.etudiantproject.Note.NotesManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list/note")
public class NotesManagementController {
    @Autowired
    private NotesManagementService notesParMatiereService;

    @GetMapping("/notes/{matiereID}")
    public NotesParMatiereResponse getNotesByMatiere(@PathVariable Long matiereID) {
        return notesParMatiereService.getNotesByMatiere(matiereID);
    }

    @GetMapping("/etudiants/rattrapage/{classeID}")
    public List<EtudiantEnRattrapage> getEtudiantsEnRattrapageParClasse(@PathVariable Long classeID) {
        return notesParMatiereService.getEtudiantsEnRattrapageParClasse(classeID);
    }
}
