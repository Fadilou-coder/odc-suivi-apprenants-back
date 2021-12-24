package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.BriefApi;
import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public List<BriefDto> findByFormateur(Long id) {
        return briefService.findByFormateur(id);
    }

    @Override
    public List<BriefDto> findByApprenant(Long id) {
        return briefService.findByApprenant(id);
    }

    @Override
    public BriefDto duopliquerBrief(Long id) {
        return briefService.dupliquerBrief(id);
    }

    @Override
    public BriefDto putBrief(Long id, String titre, String description, String contexte, String modalitePedagodiques, String criterePerformances, String modaliteEvaluations, MultipartFile image, List<String> tags, List<String> competences, List<Long> niveaux) throws Exception {
        return briefService.putBrief(id, titre, description, contexte, modalitePedagodiques, criterePerformances, modaliteEvaluations, image, tags, competences, niveaux);
    }

    @Override
    public LivrablesPartielsDto addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id) {
        return briefService.addLivrablesPartiels(livrablesPartielsDto, id);
    }

    @Override
    public Collection<LivrablesPartielsDto> ListLivrablesPartiels(Long id) {
        return briefService.ListLivrablesPartiels(id);
    }

    @Override
    public Collection<LivrablesDto> addUrl(Collection<LivrablesDto> livrablesDtos, Long id, Long idApp) {
        return briefService.addUrl(livrablesDtos, id, idApp);
    }

    @Override
    public Collection<LivrablesDto> findUrl(Long id, Long idApp) {
        return briefService.findUrl(id, idApp);
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(Long id, Long idApp) {
        return briefService.findLivrablesPartielsByAprrenant(id, idApp);
    }

    @Override
    public LivrablesPartielsDto rendreLivrablePartiel(Long id, Long idApp, Long idLp) {
        return briefService.rendreLivrablePartiel(id, idApp, idLp);
    }

    @Override
    public BriefDto cloturerBrief(Long id) {
        return briefService.cloturerBrief(id);
    }

    @Override
    public BriefDto ArchiverBrief(Long id) {
        return briefService.archiverBrief(id);
    }

    @Override
    public CompetenceValideDto validerCompetence(Long id, Long idComp) {
        return briefService.validerCompetence(id, idComp);
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesACorrigerByAprrenant(Long id, Long idApp) {
        return briefService.findLivrablesACorrigerByAprrenant(id, idApp);
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesARefaireByAprrenant(Long id, Long idApp) {
        return briefService.findLivrablesARefaireByAprrenant(id, idApp);
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesValidesByAprrenant(Long id, Long idApp) {
        return briefService.findLivrablesValidesByAprrenant(id, idApp);
    }

    @Override
    public LivrablesPartielsDto corrigerLivrable(Long id, String status) {
        return briefService.corrigerLivrable(id, status);
    }

    @Override
    public LivrablesPartielsDto deleteLivrable(Long id) {
        return briefService.deleteLivrable(id);
    }

    @Override
    public Collection<GroupeDto> addApprenantsToBriefs(Long id, Collection<GroupeDto> groupeDto) {
        return briefService.addApprenantsToBriefs(id, groupeDto);
    }

}
