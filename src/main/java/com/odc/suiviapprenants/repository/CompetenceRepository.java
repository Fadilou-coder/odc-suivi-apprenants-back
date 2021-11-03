package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.GroupeCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    Optional<Competence> findByLibelleAndArchiveFalse(String libelle);

    List<Competence> findAllByArchiveFalse();
    List<Competence> findAllByGroupeCompetencesId(Long id);

//    List<Competence> findAllByGroupeCompetences(Collection<GroupeCompetence> groupeCompetences);
}
