package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

    Optional<Materiel> findByLibelleAndAndDescriptionAndArchiveFalse(String libelle, String description);
}
