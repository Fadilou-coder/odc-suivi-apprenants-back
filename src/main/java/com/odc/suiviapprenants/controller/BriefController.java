package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.BriefApi;
import com.odc.suiviapprenants.dto.BriefDto;
import com.odc.suiviapprenants.dto.LivrablesPartielsDto;
import com.odc.suiviapprenants.dto.LivrablesRendusDto;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
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
            List<String> tags,
            List<String> groupes,
            List<String> apprenants,
            List<String> competences,
            List<Long> niveaux
    ) throws Exception {
        return briefService.save(
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
        );
    }

    @Override
    public BriefDto findBriefById(Long id) {
        return briefService.findBriefById(id);
    }

    @Override
    public LivrablesPartielsDto addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id) {
        return briefService.addLivrablesPartiels(livrablesPartielsDto, id);
    }

    @Override
    public Collection<LivrablesPartielsDto> ListLivrablesPartiels(Long id) {
        return null;
    }

    @Override
    public Collection<LivrablesRendusDto> addUrl(Collection<LivrablesRendusDto> livrablesRendusDtos, Long id, Long idApp) {
        return null;
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(Long id, Long idApp) {
        return null;
    }

    @Override
    public LivrablesPartielsDto rendreLivrablePartiel(Long id, Long idApp, Long idLp) {
        return null;
    }

    @Override
    public BriefDto cloturerBrief(Long id) {
        return null;
    }

}
