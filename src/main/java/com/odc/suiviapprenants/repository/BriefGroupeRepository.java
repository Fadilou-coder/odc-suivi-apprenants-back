package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.BriefGroupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BriefGroupeRepository extends JpaRepository<BriefGroupe, Long> {
    List<BriefGroupe> findByBriefId(Long brief_id);
}
