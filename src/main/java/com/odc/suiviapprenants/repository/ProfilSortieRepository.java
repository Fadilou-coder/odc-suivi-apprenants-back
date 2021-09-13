package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.ProfilSortie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProfilSortieRepository extends PagingAndSortingRepository<ProfilSortie, Long> {
    List<ProfilSortie> findAllByArchiveFalse();
    Optional<ProfilSortie> findByLibelle(String libelle);
}
