package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
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

    List<BriefDto> findByFormateur(Long id);

    List<BriefDto> findByApprenant(Long id);

    BriefDto dupliquerBrief(Long id);

    BriefDto putBrief(
            Long id,
            String titre,
            String description,
            String contexte,
            String modalitePedagodiques,
            String criterePerformances,
            String modaliteEvaluations,
            MultipartFile image,
            List<String> tags,
            List<String> competences,
            List<Long> niveaux
    ) throws Exception;

    LivrablesPartielsDto addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id);

    Collection<LivrablesPartielsDto> ListLivrablesPartiels(Long id);

    Collection<LivrablesDto> addUrl(Collection<LivrablesDto> livrablesDtos, Long id, Long idApp);

    Collection<LivrablesDto> findUrl(Long id, Long idApp);

    Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(Long id, Long idApp);

    LivrablesPartielsDto rendreLivrablePartiel(Long id, Long idApp, Long idLp);

    BriefDto cloturerBrief(Long id);

    BriefDto archiverBrief(Long id);

    Collection<BriefCompetenceDto> listCompByBriefByApprenant(Long id, Long idBr);

    CompetenceValideDto validerCompetence(Long id, Long idComp);

    Collection<LivrablesPartielsDto> findLivrablesACorrigerByAprrenant(Long id, Long idApp);

    Collection<LivrablesPartielsDto> findLivrablesARefaireByAprrenant(Long id, Long idApp);

    Collection<LivrablesPartielsDto> findLivrablesValidesByAprrenant(Long id, Long idApp);

    LivrablesPartielsDto corrigerLivrable(Long id, String status);

    LivrablesPartielsDto deleteLivrable(Long id);

    Collection<GroupeDto> addApprenantsToBriefs(Long id, Collection<GroupeDto> groupeDto);

    Collection<ApprenantDto> findApprenantsByBrief(Long id);

    Collection<BriefCompetenceDto> findCompetencesByBrief(Long id);


}
