package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.Apprenant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApprenantRepository extends PagingAndSortingRepository<Apprenant, Long> {

    Apprenant findByUsername(String username);
}
