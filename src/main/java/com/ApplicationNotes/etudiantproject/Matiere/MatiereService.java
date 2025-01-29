package com.ApplicationNotes.etudiantproject.Matiere;


import com.ApplicationNotes.etudiantproject.Classe.Classe;
import com.ApplicationNotes.etudiantproject.Classe.ClasseRepository;
import com.ApplicationNotes.etudiantproject.Etudiant.Etudiant;
import com.ApplicationNotes.etudiantproject.Etudiant.EtudiantDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;
    private final ClasseRepository classeRepository;

    public MatiereService(MatiereRepository matiereRepository, ClasseRepository classeRepository) {
        this.matiereRepository = matiereRepository;
        this.classeRepository = classeRepository;
    }

    private Matiere convertDTOToEntity(MatiereDTO matiereDTO) {
        Matiere matiere = new Matiere();
        matiere.setNom(matiereDTO.getNom());

        Classe classe = classeRepository.findById(matiereDTO.getClasseID())
                .orElseThrow(() -> new RuntimeException("Classe not found with id " + matiereDTO.getClasseID()));

        matiere.setClasse(classe);
        return matiere;
    }

    @Transactional
    public Matiere add(MatiereDTO matiereDTO) {
        Matiere matiere = convertDTOToEntity(matiereDTO);
        return matiereRepository.save(matiere);
    }

    @Transactional(readOnly = true)
    public List<Matiere> getMatieres() {
        return matiereRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Matiere> getById(Long id) {
        return matiereRepository.findById(id);
    }


    @Transactional
    public void deleteById(Long id) {
        matiereRepository.deleteById(id);
    }

    @Transactional
    public Matiere update(MatiereDTO matiereDTO, Long id) {

        Matiere existingMatiere = matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matiere not found with id " + id));


        existingMatiere.setNom(matiereDTO.getNom());

        Classe classe = classeRepository.findById(matiereDTO.getClasseID())
                .orElseThrow(() -> new RuntimeException("Classe not found with id " + matiereDTO.getClasseID()));
        existingMatiere.setClasse(classe);

        return matiereRepository.save(existingMatiere);

    }
}
