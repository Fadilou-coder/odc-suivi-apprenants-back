package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Livrable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    List<Livrable> findByBriefApprenantId(Long briefApprenant_id);
}
