package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.FormateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.odc.suiviapprenants.dto.PromoDto;

import java.io.IOException;

@Api("formataeur")
public interface FormateurApi {
    @PostMapping("/admin/createFormateur")
    FormateurDto save(
            @RequestParam("username") String username ,
            @RequestParam("email") String email,
            @RequestParam("prenom") String prenom,
            @RequestParam("nom") String nom,
            @RequestParam("numeroTelephone") String telephone,
            @RequestParam("role") String role,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("dateNaissance") String dateNaissance

    );

    @GetMapping("/formateurs/promoEncours")
    PromoDto promoEncours();
}
