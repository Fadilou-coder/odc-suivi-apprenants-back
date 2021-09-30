package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.PromoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PromoService {
    PromoDto save(
            String langue,
            String referenceAgate,
            String title,
            String description,
            String lieu,
            String dateDebut,
            String dateFinProvisoir,
            String dateFinReelle,
            String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String> apprenantsEmail
    ) throws Exception;

    List<PromoDto> findAll();

    PromoDto findById(Long id);

    PromoDto put(PromoDto promoDto,Long id);

}
