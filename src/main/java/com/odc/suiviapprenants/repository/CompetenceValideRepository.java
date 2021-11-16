package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.CompetenceValide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompetenceValideRepository extends JpaRepository<CompetenceValide, Long> {
    Optional<CompetenceValide> findByCompetenceIdAndApprenantId(Long competence_id, Long apprenant_id);
}
