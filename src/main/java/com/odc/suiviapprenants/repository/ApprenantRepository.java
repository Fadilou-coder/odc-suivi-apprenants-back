package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    Apprenant findByUsernameAndArchiveFalse(String username);

    List<Apprenant> findAllByArchiveFalse();

    Optional<Apprenant> findByIdAndArchiveFalse(Long id);

    Optional<Apprenant> findByEmail(String email);

    List<Apprenant> findByGroupes(Groupe groupe);

    List<Apprenant> findByProfilSorties(ProfilSortie profilSortie);
}
