package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.BriefApprenant;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.LivrablePartiel;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BriefServiceImpl implements BriefService {

    ApplicationService applicationService;
    BriefRepository briefRepository;
    FormateurRepository formateurRepository;
    TagRepository tagRepository;
    GroupeRepository groupeRepository;
    ApprenantRepository apprenantRepository;
    CompetenceRepository competenceRepository;
    NiveauEvaluationRepository niveauEvaluationRepository;
    LivrablePartielRepository livrablePartielRepository;
    BriefCompetenceRepository briefCompetenceRepository;
    BriefApprenantRepository briefApprenantRepository;
    BriefGroupeRepository briefGroupeRepository;

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
        PromoDto promo = applicationService.promoEncours();
        if (promo == null)  return null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Formateur formateur = formateurRepository.findByUsernameAndArchiveFalse(username).get();

        List<TagDto> tagList = new ArrayList<>();
        List<BriefGroupeDto> groupeList = new ArrayList<>();
        List<BriefApprenantDto>  apprenantList = new ArrayList<>();
        List<BriefCompetenceDto> competenceList = new ArrayList<>();
        List<RessourcesDto> ressourcesDtos = new ArrayList<>();
        List<LivrablesDto> livrablesDtos = new ArrayList<>();

        List<LivrablesAttendusDto> livrablesAttendusList = new ArrayList<>(Arrays.asList(
                new LivrablesAttendusDto(null, "Github", livrablesDtos),
                new LivrablesAttendusDto(null, "Trello", livrablesDtos),
                new LivrablesAttendusDto(null, "Figma", livrablesDtos),
                new LivrablesAttendusDto(null, "Deploiement", livrablesDtos)
        ));

        if (!tags.isEmpty()){
            tags.forEach(tag ->{
                tagList.add(TagDto.fromEntity(tagRepository.findByLibelleAndArchiveFalse(tag).get()));
            });
        }

        BriefDto briefDto = new BriefDto(
                null,
                titre,
                description,
                contexte,
                LocalDate.now(),
                modalitePedagodiques,
                criterePerformances,
                modaliteEvaluations,
                "Brouillon",
                false,
                image.getBytes(),
                tagList,
                ressourcesDtos,
                FormateurDto.fromEntity(formateur),
                promo,
                groupeList,
                apprenantList,
                competenceList,
                livrablesAttendusList
        );
        briefDto =  BriefDto.fromEntity(briefRepository.save(BriefDto.toEntity(briefDto)));

        if (!competences.isEmpty()){
            AtomicInteger i = new AtomicInteger();
            BriefDto finalBriefDto = briefDto;
            competences.forEach(comp -> {
                competenceList.add(
                        BriefCompetenceDto.fromEntity(briefCompetenceRepository.save(BriefCompetenceDto.toEntity( new BriefCompetenceDto(null, finalBriefDto, CompetenceDto.fromEntity(competenceRepository.findByLibelleAndArchiveFalse(comp).get()), NiveauEvaluationDto.fromEntity(niveauEvaluationRepository.findById(niveaux.get(i.getAndIncrement())).get()), false))))
                );
            });
        }

        if (!groupes.isEmpty()){
            BriefDto finalBriefDto1 = briefDto;
            groupes.forEach(groupe -> {
                groupeList.add(
                      BriefGroupeDto.fromEntity(briefGroupeRepository.save(BriefGroupeDto.toEntity(new BriefGroupeDto(null,  GroupeDto.fromEntity(groupeRepository.findByNomGroupeAndPromo(groupe, PromoDto.toEntity(promo)).get()), finalBriefDto1, false))))
                );
                groupeRepository.findByNomGroupeAndPromo(groupe, PromoDto.toEntity(promo)).get()
                        .getApprenants().forEach(app -> {
                            apprenantList.add(
                                    BriefApprenantDto.fromEntity(briefApprenantRepository.save(BriefApprenantDto.toEntity(new BriefApprenantDto(null, finalBriefDto1, ApprenantDto.fromEntity(app), null, null, false))))
                            );
                        });
            });
        }

        if (!apprenants.isEmpty()){
            BriefDto finalBriefDto2 = briefDto;
            apprenants.forEach(app -> {
                apprenantList.add(
                    new BriefApprenantDto(null, finalBriefDto2, ApprenantDto.fromEntity(apprenantRepository.findByUsernameAndArchiveFalse(app)), null, null, false)
                );
            });
        }
        briefDto.setBriefGroupes(groupeList);
        briefDto.setBriefApprenants(apprenantList);
        briefDto.setBriefCompetences(competenceList);


       return briefDto;
    }

    @Override
    public List<BriefDto> findAll() {

        PromoDto promo = applicationService.promoEncours();
        if (promo == null)  return null;

        return briefRepository.findAllByArchiveFalseAndPromoId(promo.getId()).stream()
                .map(BriefDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BriefDto findBriefById(Long id) {
        if (id == null) {
            return null;
        }
        PromoDto promo = applicationService.promoEncours();
        if (promo == null)  return null;

        return briefRepository.findByIdAndArchiveFalseAndPromoId(id, promo.getId())
                .map(BriefDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Brief  avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCodes.BRIEF_NOT_FOUND)
                );
    }

    @Override
    public LivrablesPartielsDto  addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id) {

        Collection<BriefApprenantDto> briefApprenantDtos = new ArrayList<>();
        if (livrablesPartielsDto.getBriefApprenant().size() > 1)
            livrablesPartielsDto.setType("groupe");
        else
            livrablesPartielsDto.setType("individuel");
        livrablesPartielsDto.getBriefApprenant().forEach( briefApprenantDto -> {
            briefApprenantDtos.add(BriefApprenantDto.fromEntity(briefApprenantRepository.findByBriefIdAndApprenantId(id, briefApprenantDto.getApprenant().getId())));
        });
        livrablesPartielsDto.setBriefApprenant(briefApprenantDtos);
        return LivrablesPartielsDto.fromEntity(livrablePartielRepository.save(LivrablesPartielsDto.toEntity(livrablesPartielsDto)));
    }
}
