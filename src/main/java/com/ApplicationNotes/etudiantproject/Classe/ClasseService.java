package com.ApplicationNotes.etudiantproject.Classe;

import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantDTO;
import com.ApplicationNotes.etudiantproject.Filiere.Filiere;
import com.ApplicationNotes.etudiantproject.Filiere.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final FiliereRepository filiereRepository;

    @Autowired
    public ClasseService(ClasseRepository classeRepository, FiliereRepository filiereRepository) {
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;
    }

    private Classe convertDTOToEntity(ClasseDTO classeDTO) {
        Classe classe = new Classe();
        classe.setNom(classeDTO.getNom());
        classe.setNiveau(classeDTO.getNiveau());


        Filiere filiere = filiereRepository.findById(classeDTO.getFiliereID())
                .orElseThrow(() -> new RuntimeException("Filiere not found with id " + classeDTO.getFiliereID()));

        classe.setFiliere(filiere);
        return classe;
    }

    @Transactional
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    }

    @Transactional
    public Optional<Classe> getClasseById(Long id) {
        return classeRepository.findById(id);
    }

    @Transactional
    public Classe addClasse(ClasseDTO classeDTO) {
        Classe classe = convertDTOToEntity(classeDTO);
        return classeRepository.save(classe);
    }

    @Transactional
    public Classe updateClasse(Long id, ClasseDTO classeDTO) {
        Classe existingClasse = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe not found with id " + id));


        existingClasse.setNom(classeDTO.getNom());
        existingClasse.setNiveau(classeDTO.getNiveau());

        Filiere filiere = filiereRepository.findById(classeDTO.getFiliereID())
                .orElseThrow(() -> new RuntimeException("Filiere not found with id " + classeDTO.getFiliereID()));
        existingClasse.setFiliere(filiere);

        return classeRepository.save(existingClasse);
    }

    @Transactional
    public void deleteByIdClasse(Long id) {
        classeRepository.deleteById(id);
    }

}
