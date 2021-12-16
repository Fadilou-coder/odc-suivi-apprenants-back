package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.BriefGroupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BriefGroupeRepository extends JpaRepository<BriefGroupe, Long> {
    List<BriefGroupe> findByBriefId(Long brief_id);
    Optional<BriefGroupe> findByBriefIdAndGroupeId(Long brief_id, Long groupe_id);
}
