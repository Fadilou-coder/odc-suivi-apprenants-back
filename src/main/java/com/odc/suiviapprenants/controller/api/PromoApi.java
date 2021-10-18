package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.model.Groupe;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Api("promo")
public interface PromoApi {

    @PostMapping("/promo/create")
    PromoDto save(
            @RequestParam("langue") String langue,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("lieu") String lieu,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFinProvisoir") String dateFinProvisoir,
            @RequestParam("dateFinReelle") String dateFinReelle,
            @RequestParam("etat") String etat,
            @RequestParam("avatarPromo")MultipartFile avatarPromo,
            @RequestParam("referentiel") String referentiel,
            @RequestParam("apprenantsEmail") List<String> apprenantsEmail
    ) throws Exception;
    @GetMapping("/promo")
    List<PromoDto> findAll();

    @GetMapping("/promo/{id}")
    PromoDto findById(@PathVariable Long id);

    @PutMapping("/promo/{id}")
    PromoDto put(@RequestBody PromoDto promoDto ,@PathVariable Long id);
}
