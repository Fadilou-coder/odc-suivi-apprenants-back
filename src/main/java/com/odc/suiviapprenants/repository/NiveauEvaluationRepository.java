package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.NiveauEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface NiveauEvaluationRepository extends JpaRepository<NiveauEvaluation, Long> {
    List<NiveauEvaluation> findAllByArchiveFalse();
    Optional<NiveauEvaluation> findByIdAndArchiveFalse(Long id);
}
