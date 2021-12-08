package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.BriefApprenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BriefApprenantRepository extends JpaRepository<BriefApprenant, Long> {
    Optional<BriefApprenant> findByBriefIdAndApprenantId(Long brief_id, Long apprenant_id);
    List<BriefApprenant> findAllByApprenantId(Long id);
}
