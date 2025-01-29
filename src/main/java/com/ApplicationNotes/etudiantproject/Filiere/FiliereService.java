package com.ApplicationNotes.etudiantproject.Filiere;

import com.ApplicationNotes.etudiantproject.Classe.Classe;
import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Transactional
    public Filiere add(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @Transactional(readOnly = true)
    public List<Filiere> getFilieres() {
        return filiereRepository.findAll();
    }

    @Transactional
    public Optional<Filiere> getFiliereById(Long id) {
        return filiereRepository.findById(id);
    }

    @Transactional
    public void deleteByIdFiliere(Long id) {
        filiereRepository.deleteById(id);
    }

    @Transactional
    public Filiere update(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

}
