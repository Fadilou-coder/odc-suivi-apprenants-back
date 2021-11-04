package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.BriefApi;
import com.odc.suiviapprenants.dto.BriefDto;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BriefController implements BriefApi {

    BriefService briefService;

    @Override
    public List<BriefDto> findAll() {
        return briefService.findAll();
    }

    @Override
    public BriefDto save(
            String titre,
            String description,
            String contexte,
            String modalitePedagodiques,
            String criterePerformances,
            String modaliteEvaluations,
            MultipartFile image,
            String tags,
            String groupes,
            String apprenants,
            String competences,
            String niveaux
    ) throws IOException {
        return null;
        /*return briefService.save(
                titre,
                description,
                contexte,
                modalitePedagodiques,
                criterePerformances,
                modaliteEvaluations,
                image,
                tags,
                groupes,
                apprenants,
                competences,
                niveaux
        );*/
    }

    @Override
    public BriefDto findBriefById(Long id) {
        return null;
    }

}
