package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.BriefDto;
import com.odc.suiviapprenants.dto.LivrablesPartielsDto;
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
            List<String> tags,
            List<String> groupes,
            List<String> apprenants,
            List<String> competences,
            List<Long> niveaux
    ) throws Exception;

    List<BriefDto> findAll();

    BriefDto findBriefById(Long id);

    LivrablesPartielsDto addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id);


}
