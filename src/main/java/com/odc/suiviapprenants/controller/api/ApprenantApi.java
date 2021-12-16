package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.ApprenantDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("apprenant")
public interface ApprenantApi {

    @PostMapping("/apprenants/create")
    ApprenantDto save(
            @RequestParam("username") String username ,
            @RequestParam("email") String email,
            @RequestParam("prenom") String prenom,
            @RequestParam("nom") String nom,
            @RequestParam("numeroTelephone") String telephone,
            @RequestParam("adresse") String adresse,
            @RequestParam("cni") String cni,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("dateNaissance") String dateNaissance,
            @RequestParam("motif") String motif,
            @RequestParam("genre") String genre,
            @RequestParam("niveauEntree") String niveauEntree,
            @RequestParam("handicap") String handicap,
            @RequestParam("orphelin") String orphelin,
            @RequestParam("etablissementPrecedent") String etablissementPrecedent
            ) throws IOException;

    @GetMapping("/apprenants")
    List<ApprenantDto> findAll();

    @GetMapping("/apprenants/{id}")
    ApprenantDto findById(@PathVariable Long id);


    @DeleteMapping("/apprenants/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/apprenants/{id}")
    ApprenantDto put(@PathVariable Long id,
                     @RequestParam("username") String username ,
                     @RequestParam("email") String email,
                     @RequestParam("prenom") String prenom,
                     @RequestParam("nom") String nom,
                     @RequestParam("numeroTelephone") String telephone,
                     @RequestParam("adresse") String adresse,
                     @RequestParam("cni") String cni,
                     @RequestParam("avatar") MultipartFile avatar,
                     @RequestParam("dateNaissance") String dateNaissance,
                     @RequestParam("etat") String etat,
                     @RequestParam("motif") String motif,
                     @RequestParam("genre") String genre,
                     @RequestParam("niveauEntree") String niveauEntree,
                     @RequestParam("handicap") String handicap,
                     @RequestParam("orphelin") String orphelin,
                     @RequestParam("etablissementPrecedent") String etablissementPrecedent
                     ) throws IOException;
}
