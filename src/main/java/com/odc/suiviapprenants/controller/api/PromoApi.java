package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Apprenant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Api("promo")
public interface PromoApi {

    @PostMapping(value = "/promos/create")
    PromoDto save(
            @RequestParam("langue") String langue,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("lieu") String lieu,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFinProvisoire") String dateFinProvisoire,
            @RequestParam("etat") String etat,
            @RequestParam("avatarPromo")MultipartFile avatarPromo,
            @RequestParam("referentiel") String referentiel,
            @RequestParam("apprenantsEmail") List<String> apprenantsEmail,
            @RequestParam("formateurs") List<String> formateurs
    ) throws Exception;
    @GetMapping("/promos")
    List<PromoDto> findAll();

    @GetMapping("/promos/{id}")
    PromoDto findById(@PathVariable Long id);

    @GetMapping("/promos/{id}/apprenants")
    List<ApprenantDto> findApprenantsByPromoId(@PathVariable Long id);

    @PutMapping("/promos/{id}")
    PromoDto put(@RequestBody PromoDto promoDto, @PathVariable Long id);

    @DeleteMapping("/promos/{id}")
    PromoDto delete(@PathVariable Long id);
}
