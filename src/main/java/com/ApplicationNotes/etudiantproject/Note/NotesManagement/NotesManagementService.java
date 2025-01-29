package com.ApplicationNotes.etudiantproject.Note.NotesManagement;

import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantRepository;
import com.ApplicationNotes.etudiantproject.Matiere.Matiere;
import com.ApplicationNotes.etudiantproject.Matiere.MatiereRepository;
import com.ApplicationNotes.etudiantproject.Note.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesManagementService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;  // Ajout de l'annotation @Autowired

    @Autowired
    private NoteRepository noteRepository;

    @Transactional(readOnly = true)
    public NotesParMatiereResponse getNotesByMatiere(Long matiereID) {

        Matiere matiere = matiereRepository.findById(matiereID)
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + matiereID));

        List<Note> notes = noteRepository.findAll().stream()
                .filter(note -> note.getMatiere().getMatiereID().equals(matiereID))
                .toList();


        List<EtudiantNote> etudiantNotes = notes.stream()
                .map(note -> new EtudiantNote(note.getEtudiant().getEtudiantID(), note.getNote()))
                .toList();

        return new NotesParMatiereResponse(matiereID, etudiantNotes);
    }



    @Transactional(readOnly = true)
    public List<EtudiantEnRattrapage> getEtudiantsEnRattrapageParClasse(Long classeID) {
        List<Etudiant> etudiants = etudiantRepository.findByClasse_ClasseID(classeID);

        List<Note> notes = noteRepository.findAll();

        List<Matiere> matieres = matiereRepository.findAll();

        return etudiants.stream()
                .map(etudiant -> {
                    List<EtudiantEnRattrapage.MatiereNote> matieresEnRattrapage = notes.stream()
                            .filter(note -> note.getEtudiant().getEtudiantID().equals(etudiant.getEtudiantID())) // Filtrer les notes de l'étudiant
                            .filter(note -> note.getNote() < 10)
                            .map(note -> new EtudiantEnRattrapage.MatiereNote(
                                    note.getMatiere().getNom(),
                                    note.getNote()
                            ))
                            .collect(Collectors.toList());

                    if (!matieresEnRattrapage.isEmpty()) {
                        return new EtudiantEnRattrapage(
                                etudiant.getEtudiantID(),
                                etudiant.getNom(),
                                etudiant.getPrenom(),
                                etudiant.getClasse().getClasseID(),
                                etudiant.getClasse().getNom(),
                                matieresEnRattrapage
                        );
                    }
                    return null;
                })
                .filter(etudiant -> etudiant != null)
                .collect(Collectors.toList());
    }
}
