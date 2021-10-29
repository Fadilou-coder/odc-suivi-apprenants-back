package com.odc.suiviapprenants.repository;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PromoRepository extends JpaRepository<Promo , Long> {
    List<Promo> findAllByArchiveFalse();
    Optional<Promo> findByEnCoursTrueAndArchiveFalseAndAdmins(Admin admins);
    Optional<Promo> findByEnCoursTrueAndArchiveFalseAndFormateurs(Formateur formateur);
    Optional<Promo> findByIdAndArchiveFalse(Long id);
}
