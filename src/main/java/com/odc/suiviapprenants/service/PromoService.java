package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Apprenant;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface PromoService {
    PromoDto save(
            String langue,
            String title,
            String description,
            String lieu,
            String dateDebut,
            String dateFinProvisoire,
            String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String> apprenantsEmail,
            List<String> formateurs
    ) throws Exception;

    List<PromoDto> findAll();

    PromoDto findById(Long id);

    PromoDto put(PromoDto promoDto,Long id);

    Collection<Apprenant> findApprenantsByPromoId(Long id);
}
