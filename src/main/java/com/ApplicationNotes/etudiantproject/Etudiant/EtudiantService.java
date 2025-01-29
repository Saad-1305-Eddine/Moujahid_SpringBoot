package com.ApplicationNotes.etudiantproject.Etudiant;
import com.ApplicationNotes.etudiantproject.*;

import com.ApplicationNotes.etudiantproject.Classe.Classe;
import com.ApplicationNotes.etudiantproject.Classe.ClasseRepository;
import com.ApplicationNotes.etudiantproject.Note.Note;
import com.ApplicationNotes.etudiantproject.Note.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtudiantService {


    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final NoteRepository noteRepository;

    public EtudiantService(EtudiantRepository etudiantRepository, ClasseRepository classeRepository, NoteRepository noteRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
        this.noteRepository = noteRepository;
    }

    private Etudiant convertDTOToEntity(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setDateNaissance(LocalDate.parse(etudiantDTO.getDateNaissance()));


        Classe classe = classeRepository.findById(etudiantDTO.getClasseID())
                .orElseThrow(() -> new RuntimeException("Classe not found with id " + etudiantDTO.getClasseID()));

        etudiant.setClasse(classe);
        return etudiant;
    }

    @Transactional
    public Etudiant add(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = convertDTOToEntity(etudiantDTO);
        return etudiantRepository.save(etudiant);
    }

    @Transactional(readOnly = true)
    public List<Etudiant> getEtudiants() {
        return etudiantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Etudiant> getById(Long id) {
        return etudiantRepository.findById(id);
    }


    @Transactional
    public void deleteById(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Transactional
    public Etudiant update(EtudiantDTO etudiantDTO, Long id) {

        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + id));


        existingEtudiant.setNom(etudiantDTO.getNom());
        existingEtudiant.setPrenom(etudiantDTO.getPrenom());
        existingEtudiant.setDateNaissance(LocalDate.parse(etudiantDTO.getDateNaissance()));


        Classe classe = classeRepository.findById(etudiantDTO.getClasseID())
                .orElseThrow(() -> new RuntimeException("Classe not found with id " + etudiantDTO.getClasseID()));
        existingEtudiant.setClasse(classe);

        return etudiantRepository.save(existingEtudiant);

    }

    @Transactional
    public List<Etudiant> afficherEtudiantsParClasse(Long classeID) {

        List<Etudiant> etudiants = etudiantRepository.findAll();

        return etudiants.stream()
                .filter(etudiant -> etudiant.getClasse().getClasseID().equals(classeID))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public double calculerNoteGenerale(Long etudiantID) {
        Etudiant etudiant = etudiantRepository.findById(etudiantID)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + etudiantID));

        List<Note> notes = noteRepository.findByEtudiant(etudiant);

        double sommeDesNotes = 0;
        int nombreDeMatieres = 0;

        for (Note note : notes) {
            sommeDesNotes += note.getNote();
            nombreDeMatieres++;
        }

        if (nombreDeMatieres > 0) {
            return sommeDesNotes / nombreDeMatieres;
        } else {
            return 0;
        }
    }

    @Transactional(readOnly = true)
    public String getStatutEtudiant(Long etudiantID) {
        double moyenne = calculerNoteGenerale(etudiantID);

        if (moyenne >= 10) {
            return "Admis";
        } else {
            return "Ajourné";
        }
    }}
