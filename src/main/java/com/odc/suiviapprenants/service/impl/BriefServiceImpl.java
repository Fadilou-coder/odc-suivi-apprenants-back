package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        /*PromoDto promo = applicationService.promoEncours();
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
        List<LivrablesAttendusDto> livrablesAttendusList = new ArrayList<>();

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
                null,
                null,
                FormateurDto.fromEntity(formateur),
                promo,
                null,
                null,
                null,
                null
        );

        if (!tags.isEmpty()){
            tags.forEach(tag ->{
                tagList.add(TagDto.fromEntity(tagRepository.findByLibelleAndArchiveFalse(tag).get()));
            });
        }
        if (!groupes.isEmpty()){
            groupes.forEach(groupe -> {
                groupeList.add(
                        new BriefGroupeDto(null,  GroupeDto.fromEntity(groupeRepository.findByNomGroupeAndPromo(groupe, PromoDto.toEntity(promo)).get()), null, false)
                );
                groupeRepository.findByNomGroupeAndPromo(groupe, PromoDto.toEntity(promo)).get()
                        .getApprenants().forEach(app -> {
                            apprenantList.add(
                                    new BriefApprenantDto(null, null, ApprenantDto.fromEntity(app), null, null, false)
                            );
                        });
            });
        }
        if (!apprenants.isEmpty()){
            apprenants.forEach(app -> {
                apprenantList.add(
                    new BriefApprenantDto(null, null, ApprenantDto.fromEntity(apprenantRepository.findByUsernameAndArchiveFalse(app)), null, null, false)
                );
            });
        }
        if (!competences.isEmpty()){
            AtomicInteger i = new AtomicInteger();
            competences.forEach( comp -> {
                competenceList.add(
                        new BriefCompetenceDto(null, null, CompetenceDto.fromEntity(competenceRepository.findByLibelleAndArchiveFalse(comp).get()), NiveauEvaluationDto.fromEntity(niveauEvaluationRepository.findById(niveaux.get(i.getAndIncrement())).get()), false)
                );
            });
        }

        apprenantList.forEach(app -> {
            livrablesAttendusList.addAll(Arrays.asList(
                  new LivrablesAttendusDto(null, "Github", null),
                  new LivrablesAttendusDto(null, "Trello", null),
                  new LivrablesAttendusDto(null, "Figma", null),
                  new LivrablesAttendusDto(null, "Deploiement", null)
            ));
        });

        briefDto.setTags(tagList);
        briefDto.setBriefGroupes(groupeList);
        briefDto.setBriefApprenants(apprenantList);
        briefDto.setBriefCompetences(competenceList);
        briefDto.setLivrableAttendus(livrablesAttendusList);

        log.info(briefDto.toString());*/
        return null;
        /*return BriefDto.fromEntity(
                briefRepository.save(
                        BriefDto.toEntity(briefDto)
                )
        );*/
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
}
