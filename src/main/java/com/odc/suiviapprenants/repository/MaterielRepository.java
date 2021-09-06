package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.Materiel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MaterielRepository extends PagingAndSortingRepository<Materiel, Long> {
}
