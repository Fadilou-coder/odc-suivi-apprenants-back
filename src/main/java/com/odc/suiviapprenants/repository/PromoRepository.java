package com.odc.suiviapprenants.repository;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromoRepository extends JpaRepository<Promo , Long> {
    List<Promo> findAllByArchiveFalse();
    Optional<Promo> findByEnCoursTrueAndArchiveFalseAndAdmins(Admin admins);
    Optional<Promo> findByEnCoursTrueAndArchiveFalseAndFormateurs(Formateur formateur);
    Optional<Promo> findByArchiveFalseAndGroupes(Groupe groupes);
    Optional<Promo> findByIdAndArchiveFalse(Long id);
    Optional<Promo> findByEnCoursTrueAndFormateurs(Formateur formateur);
}
