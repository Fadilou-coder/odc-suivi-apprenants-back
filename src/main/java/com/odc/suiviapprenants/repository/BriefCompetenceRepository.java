package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.BriefCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BriefCompetenceRepository extends JpaRepository<BriefCompetence, Long> {
    Optional<BriefCompetence> findByCompetenceId(Long aLong);
    List<BriefCompetence> findByBriefId(Long brief_id);
}
