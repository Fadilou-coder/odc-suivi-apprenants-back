package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.FormateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("formataeur")
public interface FormateurApi {
    @PostMapping("/admins/createFormateur")
    FormateurDto save(@RequestBody FormateurDto formateurDto);

    @PutMapping("/adminsFormateur/{id}")
    FormateurDto put(@PathVariable Long id,
                     @RequestParam("username") String username ,
                     @RequestParam("email") String email,
                     @RequestParam("prenom") String prenom,
                     @RequestParam("nom") String nom,
                     @RequestParam("numeroTelephone") String telephone,
                     @RequestParam("adresse") String adresse,
                     @RequestParam("cni") String cni,
                     @RequestParam("avatar") MultipartFile avatar,
                     @RequestParam("dateNaissance") String dateNaissance,
                     @RequestParam("libelle") List<String> libelle,
                     @RequestParam("justificatif") List<MultipartFile> justificatif
    ) throws IOException;
}


