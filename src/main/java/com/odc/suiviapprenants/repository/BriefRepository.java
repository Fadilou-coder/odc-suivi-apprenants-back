package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Brief;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BriefRepository extends JpaRepository<Brief, Long> {

    List<Brief> findAllByArchiveFalseAndPromoId(Long promo_id);
    Optional<Brief> findByIdAndArchiveFalseAndPromoId(Long id, Long promo_id);
    Optional<Brief> findByTitreAndArchiveFalseAndPromoId(String titre, Long promo_id);
    List<Brief> findAllByArchiveFalseAndFormateurId(Long id);

}
