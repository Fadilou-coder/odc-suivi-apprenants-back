package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findAllByArchiveFalse();
    Optional<Competence> findByLibelle(String libelle);
    Optional<Competence> findByLibelleAndArchiveFalse(String libelle);
}
