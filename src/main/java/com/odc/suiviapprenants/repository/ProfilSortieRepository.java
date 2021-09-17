package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.ProfilSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProfilSortieRepository extends JpaRepository<ProfilSortie, Long> {
    List<ProfilSortie> findAllByArchiveFalse();
    Optional<ProfilSortie> findByLibelle(String libelle);
}
