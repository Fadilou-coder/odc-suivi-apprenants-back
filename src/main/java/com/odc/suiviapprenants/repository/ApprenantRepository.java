package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    Apprenant findByUsernameAndArchiveFalse(String username);

    List<Apprenant> findAllByArchiveFalse();

    Optional<Apprenant> findByIdAndArchiveFalse(Long id);

    Optional<Apprenant> findByEmail(String email);

    List<Apprenant> findAllByGroupesAndGroupesNot(Groupe groupes, Groupe groupes2);
}
