package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.PromoApi;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.service.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@AllArgsConstructor
public class PromoController implements PromoApi {

    PromoService promoService;

    @Override
    public PromoDto save(
            String langue,
            String title,
            String description,
            String lieu,
            String dateDebut,
            String dateFinProvisoire,
            String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String > apprenantsEmail
    ) throws Exception {
        return promoService.save(
                langue,
                title,
                description,
                lieu,
                dateDebut,
                dateFinProvisoire,
                etat,
                avatarPromo,
                referentiel,
                apprenantsEmail
        );
    }

    @Override
    public List<PromoDto> findAll() {
        return promoService.findAll();
    }

    @Override
    public PromoDto findById(Long id) {
        return promoService.findById(id);
    }

    @Override
    public PromoDto put(PromoDto promoDto, Long id) {
        return promoService.put(promoDto, id);
    }
}
