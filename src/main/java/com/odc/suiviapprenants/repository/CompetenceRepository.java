package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompetenceRepository extends PagingAndSortingRepository<Competence, Long> {
}
