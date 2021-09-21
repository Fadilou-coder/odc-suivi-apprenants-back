package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    Optional<Competence> findByLibelleAndArchiveFalse(String libelle);

    List<Competence> findAllByArchiveFalse();
    List<Competence> findAllByGroupeCompetencesId(Long id);
}
