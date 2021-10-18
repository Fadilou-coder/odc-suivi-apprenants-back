package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Groupe;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PromoService {
    PromoDto save(
            String langue,
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
