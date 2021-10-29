package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.FormateurDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FormateurService {
    FormateurDto save(FormateurDto formateurDto);
    FormateurDto put(Long id,
                     String username ,
                     String email,
                     String prenom,
                     String nom,
                     String telephone,
                     String adresse,
                     String cni,
                     MultipartFile avatar,
                     String dateNaissance,
                     List<String> libelle,
                     List<MultipartFile> justificatif
    ) throws IOException;
}
