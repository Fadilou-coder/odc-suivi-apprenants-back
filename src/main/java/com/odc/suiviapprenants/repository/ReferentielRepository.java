package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Referentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {
}
