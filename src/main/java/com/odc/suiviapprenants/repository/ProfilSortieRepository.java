package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfilSortieRepository extends JpaRepository<ProfilSortie, Long> {
    List<ProfilSortie> findAllByArchiveFalse();
    Optional<ProfilSortie> findByLibelle(String libelle);
    List<ProfilSortie> findByPromo(Promo promo);
}
