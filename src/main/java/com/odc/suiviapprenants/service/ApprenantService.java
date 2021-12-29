package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.EmailDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ApprenantService {

    ApprenantDto save(
            String username ,
            String email,
            String prenom,
            String nom,
            String telephone,
            String adresse,
            String cni,
            MultipartFile avatar,
            String dateNaissance,
            String motif,
            String genre,
            String niveauEntree,
            String handicap,
            String orphelin,
            String etablissementPrecedent
    ) throws IOException;

    List<ApprenantDto> findAll();

    ApprenantDto findById(Long id);

    List<ApprenantDto> findByApprenantNonConnecter();

    EmailDto sendByEmail(EmailDto emailDto);

    void delete(Long id);

        ApprenantDto put(Long id,
                     String username ,
                     String email,
                     String prenom,
                     String nom,
                     String telephone,
                     String adresse,
                     String cni,
                     MultipartFile avatar,
                     String dateNaissance,
                     String etat,
                     String motif,
                     String genre,
                     String niveauEntree,
                     String handicap,
                     String orphelin,
                     String etablissementPrecedent) throws IOException;
}
