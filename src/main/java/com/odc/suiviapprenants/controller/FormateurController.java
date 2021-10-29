package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.FormateurApi;
import com.odc.suiviapprenants.dto.FormateurDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.service.FormateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
public class FormateurController implements FormateurApi {

    FormateurService formateurService;

    @Override
    public FormateurDto save(String username, String email, String prenom, String nom, String telephone, String role, MultipartFile avatar, String dateNaissance) {
        return null;
    }


}
