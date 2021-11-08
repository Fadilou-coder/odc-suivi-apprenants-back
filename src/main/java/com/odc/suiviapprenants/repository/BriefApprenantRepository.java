package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.BriefApprenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BriefApprenantRepository extends JpaRepository<BriefApprenant, Long> {
    BriefApprenant findByBriefIdAndApprenantId(Long brief_id, Long apprenant_id);
}
