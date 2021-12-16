package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.ApprenantApi;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.service.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ApprenantController implements ApprenantApi {
    ApprenantService apprenantService;

    @Override
    public ApprenantDto save(String username, String email, String prenom, String nom, String telephone, String adresse, String cni,
                             MultipartFile avatar, String dateNaissance, String motif, String genre, String niveauEntree, String handicap, String orphelin, String etablissementPrecedent) throws IOException {
        return apprenantService.save(username, email, prenom, nom, telephone, adresse, cni, avatar, dateNaissance, motif, genre, niveauEntree, handicap, orphelin, etablissementPrecedent);
    }

    @Override
    public List<ApprenantDto> findAll() {
        return apprenantService.findAll();
    }

    @Override
    public ApprenantDto findById(Long id) {
        return apprenantService.findById(id);
    }

    @Override
    public void delete(Long id) {
        apprenantService.delete(id);
    }

    @Override
    public ApprenantDto put(Long id, String username, String email, String prenom, String nom, String telephone, String adresse, String cni,
                            MultipartFile avatar, String dateNaissance, String etat,String motif, String genre, String niveauEntree, String handicap, String orphelin, String etablissementPrecedent) throws IOException {
        return apprenantService.put(id, username, email, prenom, nom, telephone, adresse, cni, avatar, dateNaissance, etat, motif, genre, niveauEntree, handicap, orphelin, etablissementPrecedent);
    }
}
