package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CompetenceRepository extends PagingAndSortingRepository<Competence, Long> {
    Optional<Competence> findByLibelleAndArchiveFalse(String libelle);
}
