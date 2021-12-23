package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.PromoApi;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.ProfilSortieDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.service.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
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
            List<String > apprenantsEmail,
            List<String> formateurs
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
                apprenantsEmail,
                formateurs
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
    public List<ApprenantDto> findApprenantsByPromoId(Long id) {
        return promoService.findApprenantsByPromoId(id);
    }

    @Override
    public List<ProfilSortieDto> findProfilSortieByPromoId(Long id) {
        return promoService.findProfilSortieByPromoId(id);
    }

    @Override
    public PromoDto put(PromoDto promoDto, Long id) {
        return promoService.put(promoDto, id);
    }

    @Override
    public PromoDto delete(Long id) {
        return promoService.delete(id);
    }
}
