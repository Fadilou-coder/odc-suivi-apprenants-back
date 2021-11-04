package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.BriefDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BriefService {

    BriefDto save(
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
    ) throws IOException;

    List<BriefDto> findAll();

    BriefDto findBriefById(Long id);
}
