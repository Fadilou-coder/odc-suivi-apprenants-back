package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    Optional<Groupe> findByIdAndPromo(Long id, Promo promo);
    List<Groupe> findAllByArchiveFalse();
    Optional<Groupe> findByIdAndArchiveFalse(Long id);
    Optional<Groupe> findByTypeAndPromoEnCoursTrueAndFormateurs(String type, Formateur formateurs);
    Optional<Groupe> findByTypeAndPromo(String type, Promo promo);
    Optional<Groupe> findByNomGroupeAndPromo(String nomGroupe, Promo promo);
    Optional<Groupe> findByNomGroupeAndApprenantsAndPromoEnCoursTrue(String nomGroupe, Apprenant apprenant);

    List<Groupe> findAllByIdNot(Long id);

    List<Groupe> findByNomGroupeNotAndFormateursIdAndPromoId(String nomGroupe, Long formateurs_id, Long promo_id);
}
