package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.ProfilSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findAllByArchiveFalse();
    Optional<Competence> findByLibelle(String libelle);
}
