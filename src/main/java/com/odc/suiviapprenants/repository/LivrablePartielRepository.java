package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.LivrablePartiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LivrablePartielRepository extends JpaRepository<LivrablePartiel, Long> {
    Optional<LivrablePartiel> findByArchiveFalseAndBriefApprenantIdAndLibelleAndDescription(Long briefApprenant_id, String libelle, String description);
    List<LivrablePartiel> findByArchiveFalseAndLibelleAndDescription(String libelle, String description);
}
