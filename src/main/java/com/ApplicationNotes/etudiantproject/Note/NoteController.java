package com.ApplicationNotes.etudiantproject.Note;


import com.ApplicationNotes.etudiantproject.Matiere.Matiere;
import com.ApplicationNotes.etudiantproject.Matiere.MatiereDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    //Seuls les etudiants appartenant a la classe de la matiére qu on peut ajouter
    //Pour eviter de donner une note d'une matiere a un etudiant, et ce dernier n'est pas dans la classe de la matiere
    @PostMapping("add")
    public Note add(@RequestBody NoteDTO noteDTO) {
        return noteService.add(noteDTO);
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return "Note deleted successfully with ID: " + id;
    }

    @GetMapping("find/{id}")
    public Note findById(@PathVariable Long id) {
        return noteService.getById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }


    @PutMapping("update/{id}")
    public Note update(@RequestBody NoteDTO noteDTO, @PathVariable Long id) {
        return noteService.update(noteDTO, id);
    }


    @GetMapping("getAll")
    public List<Note> getMatieres() {
        return noteService.getNotes();
    }

}
