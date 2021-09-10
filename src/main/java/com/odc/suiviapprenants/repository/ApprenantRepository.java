package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    Apprenant findByUsernameAndArchiveFalse(String username);

    List<Apprenant> findAllByArchiveFalse();

    Optional<Apprenant> findByIdAndArchiveFalse(Long id);
}
