package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.GroupeCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface GroupeCompetenceRepository extends JpaRepository<GroupeCompetence, Long> {
    List<GroupeCompetence> findAllByArchiveFalse();

    Optional<GroupeCompetence> findByIdAndArchiveFalse(Long id);

    Optional<GroupeCompetence> findByLibelleAndArchiveFalse(String libelle);
}
