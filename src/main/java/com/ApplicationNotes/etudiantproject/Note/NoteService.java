package com.ApplicationNotes.etudiantproject.Note;

import com.ApplicationNotes.etudiantproject.Classe.Classe;
import com.ApplicationNotes.etudiantproject.Classe.ClasseRepository;
import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantRepository;
import com.ApplicationNotes.etudiantproject.Matiere.Matiere;
import com.ApplicationNotes.etudiantproject.Matiere.MatiereDTO;
import com.ApplicationNotes.etudiantproject.Matiere.MatiereRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final MatiereRepository matiereRepository;
    private final EtudiantRepository etudiantRepository;

    public NoteService(NoteRepository noteRepository, MatiereRepository matiereRepository, EtudiantRepository etudiantRepository){
        this.noteRepository = noteRepository;
        this.matiereRepository = matiereRepository;
        this.etudiantRepository = etudiantRepository;
    }

    private Note convertDTOToEntity(NoteDTO noteDTO) {
        Note note = new Note();

        note.setNote(noteDTO.getNote());

        Matiere matiere = matiereRepository.findById(noteDTO.getMatiereID())
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + noteDTO.getMatiereID()));

        note.setMatiere(matiere);


        Etudiant etudiant = etudiantRepository.findById(noteDTO.getEtudiantID())
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + noteDTO.getEtudiantID()));

        note.setEtudiant(etudiant);
        return note;
    }

    @Transactional
    public Note add(NoteDTO noteDTO) {

        //On doit verifier tout d abord que l etudiant appartient bien a la classe de la matiére sinon on bloque l'ajout
        Etudiant etudiant = etudiantRepository.findById(noteDTO.getEtudiantID())
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + noteDTO.getEtudiantID()));

        Matiere matiere = matiereRepository.findById(noteDTO.getMatiereID())
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + noteDTO.getMatiereID()));


        if (!matiere.getClasse().getClasseID().equals(etudiant.getClasse().getClasseID())) {
            throw new RuntimeException("La matière ne correspond pas à la classe de l'étudiant");
        }
        Note note = convertDTOToEntity(noteDTO);
        return noteRepository.save(note);
    }

    @Transactional(readOnly = true)
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    @Transactional
    public Note update(NoteDTO noteDTO, Long id) {

        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));

        Matiere matiere = matiereRepository.findById(noteDTO.getMatiereID())
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + noteDTO.getMatiereID()));

        Etudiant etudiant = etudiantRepository.findById(noteDTO.getEtudiantID())
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + noteDTO.getEtudiantID()));

        if (!matiere.getClasse().getClasseID().equals(etudiant.getClasse().getClasseID())) {
            throw new RuntimeException("La matière ne correspond pas à la classe de l'étudiant");
        }

        existingNote.setNote(noteDTO.getNote());
        existingNote.setMatiere(matiere);
        existingNote.setEtudiant(etudiant);

        return noteRepository.save(existingNote);
    }






}
