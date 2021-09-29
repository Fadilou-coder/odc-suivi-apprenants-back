package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.PromoDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("promo")
public interface PromoApi {

    @PostMapping("/promo/create")
    PromoDto save(
            @RequestParam("langue") String langue,
            @RequestParam("referenceAgate") String referenceAgate,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("lieu") String lieu,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFinProvisoir") String dateFinProvisoir,
            @RequestParam("dateFinReelle") String dateFinReelle,
            @RequestParam("etat") String etat,
            @RequestParam("avatarPromo")MultipartFile avatarPromo,
            @RequestParam("referentiel") String referentiel,
            @RequestParam("groupe") String groupe
    ) throws Exception;

    @GetMapping("/promo")
    List<PromoDto> findAll();

    @GetMapping("/promo/{id}")
    PromoDto findById(@PathVariable Long id);

    @PutMapping("/promo/{id}")
    PromoDto put(@RequestBody PromoDto promoDto ,@PathVariable Long id);
}
