package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.PromoApi;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class PromoController implements PromoApi {
    @Autowired
    PromoService promoService;

    @Override
    public PromoDto save(
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
            List<String > apprenantsEmail
    ) throws Exception {
        return promoService.save(
                langue,
                title,
                description,
                lieu,
                dateDebut,
                dateFinProvisoir,
                dateFinReelle,
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
