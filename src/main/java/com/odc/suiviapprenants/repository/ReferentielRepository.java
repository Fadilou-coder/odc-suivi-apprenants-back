package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Referentiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {
    Optional<Referentiel> findByLibelle(String libelle);
    Optional<Referentiel> findByLibelleAndIdNot(String libelle, Long id);
    Optional<Referentiel> findByIdAndArchiveFalse(Long id);

    List<Referentiel> findAllByArchiveFalse();
}
