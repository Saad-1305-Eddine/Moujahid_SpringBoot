package com.ApplicationNotes.etudiantproject.Etudiant;

import com.ApplicationNotes.etudiantproject.Note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping("add")
    public Etudiant add(@RequestBody EtudiantDTO etudiantDTO) {
        return etudiantService.add(etudiantDTO);
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        etudiantService.deleteById(id);
        return "Etudiant deleted successfully with ID: " + id;
    }

    @GetMapping("find/{id}")
    public Etudiant findById(@PathVariable Long id) {
        return etudiantService.getById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + id));
    }


    @PutMapping("update/{id}")
    public Etudiant update(@RequestBody EtudiantDTO etudiantDTO, @PathVariable Long id) {
        return etudiantService.update(etudiantDTO, id);
    }


    @GetMapping("getAll")
    public List<Etudiant> getEtudiants() {
        return etudiantService.getEtudiants();
    }


    @GetMapping("/etudiants/classe/{classeID}")
    public Map<String, Object> getEtudiantsParClasse(@PathVariable Long classeID) {
        // Récupérer la liste des étudiants pour la classe donnée
        List<Etudiant> etudiants = etudiantService.afficherEtudiantsParClasse(classeID);

        // Créer la liste des étudiants sous forme de tableau [etudiantID, nom, prenom]
        List<List<Object>> etudiantsListe = etudiants.stream()
                .map(etudiant -> {
                    List<Object> etudiantInfo = new ArrayList<>();
                    etudiantInfo.add(etudiant.getEtudiantID());
                    etudiantInfo.add(etudiant.getNom());
                    etudiantInfo.add(etudiant.getPrenom());
                    return etudiantInfo;
                })
                .collect(Collectors.toList());

        // Créer la réponse sous la forme souhaitée
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("classeID", classeID); // Mettre classeID en premier
        response.put("etudiants", etudiantsListe); // Puis la liste des étudiants

        return response;
    }

    @GetMapping("/note-generale/{etudiantID}")
    public double getNoteGenerale(@PathVariable Long etudiantID) {
        return etudiantService.calculerNoteGenerale(etudiantID);
    }

    @GetMapping("/admission/{etudiantID}")
    public String getStatutEtudiant(@PathVariable Long etudiantID) {
        return etudiantService.getStatutEtudiant(etudiantID);
    }



}
