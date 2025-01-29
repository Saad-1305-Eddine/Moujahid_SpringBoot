package com.ApplicationNotes.etudiantproject.Matiere;


import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matiere")
public class MatiereController {

    private final MatiereService matiereService;

    @Autowired
    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping("add")
    public Matiere add(@RequestBody MatiereDTO matiereDTO) {
        return matiereService.add(matiereDTO);
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        matiereService.deleteById(id);
        return "Matiere deleted successfully with ID: " + id;
    }

    @GetMapping("find/{id}")
    public Matiere findById(@PathVariable Long id) {
        return matiereService.getById(id)
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + id));
    }


    @PutMapping("update/{id}")
    public Matiere update(@RequestBody MatiereDTO matiereDTO, @PathVariable Long id) {
        return matiereService.update(matiereDTO, id);
    }


    @GetMapping("getAll")
    public List<Matiere> getMatieres() {
        return matiereService.getMatieres();
    }

}
