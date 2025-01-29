package com.ApplicationNotes.etudiantproject.Classe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/classe")
public class ClasseController {

    private final ClasseService classeService;

    @Autowired
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }



    @GetMapping("getAll")
    public List<Classe> getClasses() {
        return classeService.getClasses();
    }


    @GetMapping("find/{id}")
    public Optional<Classe> getClasseById(@PathVariable Long id) {
        return classeService.getClasseById(id);
    }


    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public Classe addClasse(@RequestBody ClasseDTO classeDTO) {
        return classeService.addClasse(classeDTO);
    }


    @PutMapping("update/{id}")
    public Classe updateClasse(@PathVariable Long id, @RequestBody ClasseDTO classeDTO) {
        return classeService.updateClasse(id, classeDTO);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClasse(@PathVariable Long id) {
        classeService.deleteByIdClasse(id);
    }

}
