package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.FormateurApi;
import com.odc.suiviapprenants.dto.FormateurDto;
import com.odc.suiviapprenants.service.FormateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FormateurController implements FormateurApi {
    FormateurService formateurService;

    @Override
    public FormateurDto save(FormateurDto formateurDto) {
        return formateurService.save(formateurDto);
    }

    @Override
    public FormateurDto put(Long id, String username,
                            String email, String prenom,
                            String nom, String telephone,
                            String adresse, String cni,
                            MultipartFile avatar,
                            String dateNaissance,
                            List<String> libelle,
                            List<MultipartFile> justificatif) throws IOException {
        return formateurService.put(
                id, username,
                email, prenom,
                nom, telephone,
                adresse,
                cni, avatar,
                dateNaissance, libelle,
                justificatif
        );
    }
}
