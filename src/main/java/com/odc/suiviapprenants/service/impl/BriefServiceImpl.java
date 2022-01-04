package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.*;
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
import java.util.concurrent.atomic.AtomicBoolean;
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
    LivrableRepository livrableRepository;
    CompetenceValideRepository competenceValideRepository;
    PromoRepository promoRepository;
    LivrableAttenduRepository livrableAttenduRepository;

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
                "En cours",
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
                                    BriefApprenantDto.fromEntity(briefApprenantRepository.save(BriefApprenantDto.toEntity(new BriefApprenantDto(null, finalBriefDto1, ApprenantDto.fromEntity(app), null, null, null,  false))))
                            );
                        });
            });
        }

        if (!apprenants.isEmpty()){
            BriefDto finalBriefDto2 = briefDto;
            apprenants.forEach(app -> {
                apprenantList.add(
                    new BriefApprenantDto(null, finalBriefDto2, ApprenantDto.fromEntity(apprenantRepository.findByUsernameAndArchiveFalse(app)), null, null, null, false)
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
    public List<BriefDto> findByFormateur(Long id) {
        if (id == null){
            return null;
        }
        Formateur formateur = formateurRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Formateur avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.FORMATEUR_NOT_FOUND
                ));
        return briefRepository.findAllByArchiveFalseAndFormateurId(formateur.getId())
                .stream().map(BriefDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BriefDto> findByApprenant(Long id) {
        if (id == null){
            return null;
        }
        List<Brief> briefs = new ArrayList<>();
        Apprenant apprenant = apprenantRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Apprenant avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.APPRENANT_NOT_FOUND
                ));
        briefApprenantRepository.findAllByApprenantId(apprenant.getId()).forEach(briefApprenant -> {
            briefs.add(briefApprenant.getBrief());
        });

        return briefs.stream().map(BriefDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public BriefDto dupliquerBrief(Long id){

        if (briefRepository.findById(id).isPresent()) {
            PromoDto promo = applicationService.promoEncours();
            if (promo == null) return null;

            BriefDto briefDto = BriefDto.fromEntity(briefRepository.findById(id).get());
            Collection<BriefCompetenceDto> competenceList = briefDto.getBriefCompetences();

            List<LivrablesAttendusDto> livrablesAttendusList = new ArrayList<>(Arrays.asList(
                    new LivrablesAttendusDto(null, "Github", new ArrayList<>()),
                    new LivrablesAttendusDto(null, "Trello", new ArrayList<>()),
                    new LivrablesAttendusDto(null, "Figma", new ArrayList<>()),
                    new LivrablesAttendusDto(null, "Deploiement", new ArrayList<>())
            ));

            briefDto.setId(null);
            briefDto.setLivrableAttendus(livrablesAttendusList);
            briefDto.setBriefApprenants(new ArrayList<>());
            briefDto.setBriefGroupes(new ArrayList<>());
            briefDto.setBriefCompetences(new ArrayList<>());
            briefDto = BriefDto.fromEntity(briefRepository.save(BriefDto.toEntity(briefDto)));

            if (!briefDto.getBriefCompetences().isEmpty()){
                AtomicInteger i = new AtomicInteger();
                BriefDto finalBriefDto = briefDto;
                competenceList.forEach(comp -> {
                    BriefCompetenceDto.fromEntity(briefCompetenceRepository.save(BriefCompetenceDto.toEntity( new BriefCompetenceDto(null,
                            finalBriefDto, comp.getCompetence(), comp.getNiveau(), false))));
                });
            }
            briefDto.setBriefCompetences(competenceList);
            return briefDto;
        }
        return null;
    }

    @Override
    public BriefDto putBrief(Long id, String titre, String description, String contexte, String modalitePedagodiques, String criterePerformances, String modaliteEvaluations, MultipartFile image, List<String> tags, List<String> competences, List<Long> niveaux) throws Exception {
        if (id == null){
            return null;
        }
        Brief brief = briefRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Brief avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.BRIEF_NOT_FOUND));

        if (titre != null && !titre.equals(""))
            brief.setTitre(titre);
        if (description != null && !description.equals(""))
        brief.setDescription(description);
        if (contexte != null && !contexte.equals(""))
            brief.setContexte(contexte);
        if (criterePerformances != null && !criterePerformances.equals(""))
            brief.setCriterePerformances(criterePerformances);
        if (modalitePedagodiques != null && !modalitePedagodiques.equals(""))
            brief.setModalitePedagodiques(modalitePedagodiques);
        if (modaliteEvaluations != null && !modaliteEvaluations.equals(""))
            brief.setModaliteEvaluations(modaliteEvaluations);
        if (image != null)
            brief.setImage(image.getBytes());

        BriefDto briefDto = BriefDto.fromEntity(brief);
        List<TagDto> tagList = new ArrayList<>();
        List<BriefCompetenceDto> competenceList = new ArrayList<>();

        if (!tags.isEmpty()){
            tags.forEach(tag ->{
                if (tagRepository.findByLibelleAndArchiveFalse(tag).isPresent())
                    tagList.add(TagDto.fromEntity(tagRepository.findByLibelleAndArchiveFalse(tag).get()));
            });
        }
        brief.setTags(tagList.stream().map(TagDto::toEntity).collect(Collectors.toList()));
        brief.setBriefGroupes(new ArrayList<>());
        brief.setBriefApprenants(new ArrayList<>());
        brief.setBriefCompetences(new ArrayList<>());
        briefDto.getBriefCompetences().forEach(briefCompetenceDto -> {
            briefCompetenceRepository.delete(BriefCompetenceDto.toEntity(briefCompetenceDto));
        });
        if (!competences.isEmpty()){
            AtomicInteger i = new AtomicInteger();
            competences.forEach(comp -> {
                competenceList.add(
                        BriefCompetenceDto.fromEntity(briefCompetenceRepository.save(BriefCompetenceDto.toEntity( new BriefCompetenceDto(null, briefDto,
                                CompetenceDto.fromEntity(competenceRepository.findByLibelleAndArchiveFalse(comp).get()),
                                NiveauEvaluationDto.fromEntity(niveauEvaluationRepository.findById(niveaux.get(i.getAndIncrement())).get()), false))))
                );
            });
        }

        briefRepository.flush();
        briefCompetenceRepository.flush();
        briefDto.setBriefCompetences(competenceList);
        return briefDto;
    }

    @Override
    public LivrablesPartielsDto  addLivrablesPartiels(LivrablesPartielsDto livrablesPartielsDto, Long id) {
        if (briefRepository.findById(id).isPresent()) {
            if (livrablesPartielsDto.getId() != null){
                if (livrablePartielRepository.findById(livrablesPartielsDto.getId()).isPresent())
                    livrablePartielRepository.delete(livrablePartielRepository.findById(livrablesPartielsDto.getId()).get());
                if (briefApprenantRepository.findByBriefIdAndApprenantId(id,null).isPresent())
                    briefApprenantRepository.delete(briefApprenantRepository.findByBriefIdAndApprenantId(id,null).get());
                livrablesPartielsDto.setId(null);
            }
            livrablesPartielsDto.setId(null);
            if (livrablesPartielsDto.getApprenants().size() > 1)
                livrablesPartielsDto.setType("groupe");
            else if(livrablesPartielsDto.getApprenants().size() == 1)
                livrablesPartielsDto.setType("individuel");
            else {
                livrablesPartielsDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenantRepository.save(new BriefApprenant(briefRepository.findById(id).get(), null))));
                livrablePartielRepository.save(LivrablesPartielsDto.toEntity(livrablesPartielsDto));
            }
            livrablesPartielsDto.getApprenants().forEach(apprenantDto -> {
                if (briefApprenantRepository.findByBriefIdAndApprenantId(id, apprenantDto.getId()).isPresent()) {
                    if(!livrablePartielRepository.findByArchiveFalseAndBriefApprenantIdAndLibelleAndDescription(briefApprenantRepository.findByBriefIdAndApprenantId(id, apprenantDto.getId()).get().getId(), livrablesPartielsDto.getLibelle(), livrablesPartielsDto.getDescription()).isPresent()) {
                        livrablesPartielsDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenantRepository.findByBriefIdAndApprenantId(id, apprenantDto.getId()).get()));
                        livrablePartielRepository.save(LivrablesPartielsDto.toEntity(livrablesPartielsDto));
                    }
                }else {
                    livrablesPartielsDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenantRepository.save(new BriefApprenant(briefRepository.findById(id).get(), apprenantRepository.findById(apprenantDto.getId()).get()))));
                    livrablePartielRepository.save(LivrablesPartielsDto.toEntity(livrablesPartielsDto));
                }
            });
            return LivrablesPartielsDto.fromEntity(LivrablesPartielsDto.toEntity(livrablesPartielsDto));
        }
        return null;
    }

    @Override
    public Collection<LivrablesPartielsDto> ListLivrablesPartiels(Long id) {
        if (briefRepository.findById(id).isPresent()) {
            Brief brief = briefRepository.findById(id).get();
            Collection<LivrablePartiel> livrablesPartielsList = new ArrayList<>();
            List<String> libelles = new ArrayList<>();
            brief.getBriefApprenants().forEach(briefApprenant -> {
                briefApprenant.getLivrablePartiels().forEach(livrablePartiel -> {
                    if (!libelles.contains(livrablePartiel.getLibelle())) {
                        livrablesPartielsList.add(livrablePartiel);
                    }
                    libelles.add(livrablePartiel.getLibelle());
                });

            });
            return livrablesPartielsList.stream()
                    .map(LivrablesPartielsDto::fromEntity)
                    .collect(Collectors.toList());
        }
        else
            return new ArrayList<>();
    }

    @Override
    public Collection<LivrablesDto> addUrl(Collection<LivrablesDto> livrablesDtos, Long id, Long idApp) {
        if (briefRepository.findById(id).isPresent()) {
            if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()){
                BriefApprenant briefApprenant = briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get();
                livrablesDtos.forEach(livrablesDto -> {
                        livrablesDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenant));
                });
                return livrableRepository.saveAll(livrablesDtos.stream().map(LivrablesDto::toEntity).collect(Collectors.toList()))
                        .stream()
                        .map(LivrablesDto::fromEntity)
                        .collect(Collectors.toList());
            }
            return null;
        }
        return null;
    }

    @Override
    public Collection<LivrablesDto> findUrl(Long id, Long idApp) {
        if (briefRepository.findById(id).isPresent()) {
            if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
                BriefApprenant briefApprenant = briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get();
                return livrableRepository.findByBriefApprenantId(briefApprenant.getId())
                        .stream()
                        .map(LivrablesDto::fromEntity)
                        .collect(Collectors.toList());
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(Long id, Long idApp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            List<LivrablesPartielsDto> livrablesPartiels = new ArrayList<>();
            briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get().getLivrablePartiels().forEach(
                    livrablePartiel -> {
                        if (livrablePartiel.getLivrableRendu() == null)
                            livrablesPartiels.add(LivrablesPartielsDto.fromEntity(livrablePartiel));
                    }
            );
            return livrablesPartiels;
        }
        else
            return new ArrayList<>();
    }

    @Override
    public LivrablesPartielsDto rendreLivrablePartiel(Long id, Long idApp, Long idLp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            if (livrablePartielRepository.findById(idLp).isPresent()) {
                LivrablePartiel livrablePartiel = livrablePartielRepository.findById(idLp).get();
                if (livrablePartiel.getLivrableRendu() == null) {
                    LivrablesRendusDto livrablesRendusDto = new LivrablesRendusDto(null, "A Corriger", livrablePartiel.getDelai(), LocalDate.now(), "", LivrablesPartielsDto.fromEntity(livrablePartiel));
                    livrablePartiel.setLivrableRendu(LivrablesRendusDto.toEntity(livrablesRendusDto));
                }else {
                    livrablePartiel.getLivrableRendu().setStatut("A Corriger");
                }
                livrablePartielRepository.flush();
                return LivrablesPartielsDto.fromEntity(livrablePartielRepository.findById(idLp).get());
            }
        }
        return null;
    }

    @Override
    public BriefDto cloturerBrief(Long id) {
        if (briefRepository.findById(id).isPresent()){
            Brief brief = briefRepository.findById(id).get();
            brief.setStatut("Clôturé");
            briefRepository.flush();
            return BriefDto.fromEntity(brief);
        }
        return null;
    }

    @Override
    public BriefDto archiverBrief(Long id) {
        if (id == null){
            return null;
        }
        Brief brief = briefRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Brief avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.BRIEF_NOT_FOUND));
        brief.setArchive(true);
        briefRepository.flush();
        return BriefDto.fromEntity(brief);
    }

    @Override
    public Collection<BriefCompetenceDto> listCompByBrief(Long id) {
        if (id == null) return null;
        if (briefRepository.findById(id).isPresent()){
            return briefCompetenceRepository.findByBriefId(id).stream().map(BriefCompetenceDto::fromEntity).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public CompetenceValideDto validerCompetence(Long id, Long idComp) {
        if (briefCompetenceRepository.findByCompetenceId(idComp).isPresent()){
            CompetenceValideDto competenceValideDto = new CompetenceValideDto();
            competenceValideDto.setCompetence(CompetenceDto.fromEntity(competenceRepository.findById(idComp).get()));
            competenceValideDto.setApprenant(ApprenantDto.fromEntity(apprenantRepository.findById(id).get()));
            if (Objects.equals(briefCompetenceRepository.findByCompetenceId(idComp).get().getNiveau().getLibelle(), "Niveau 1")){
                competenceValideDto.setNiveau1(true);
            }else if (Objects.equals(briefCompetenceRepository.findByCompetenceId(idComp).get().getNiveau().getLibelle(), "Niveau 2")){
                competenceValideDto.setNiveau2(true);
            }else competenceValideDto.setNiveau3(true);

            return CompetenceValideDto.fromEntity(
                    competenceValideRepository.save(CompetenceValideDto.toEntity(competenceValideDto))
            );
        }
        return null;
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesACorrigerByAprrenant(Long id, Long idApp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            Collection<LivrablePartiel> livrablesPartielsList = new ArrayList<>();
             briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get().getLivrablePartiels().forEach(livrablePartiel -> {
                 if (livrablePartiel.getLivrableRendu() != null && !livrablesPartielsList.contains(livrablePartiel) && Objects.equals(livrablePartiel.getLivrableRendu().getStatut(), "A Corriger") )
                     livrablesPartielsList.add(livrablePartiel);
             });
            return livrablesPartielsList.stream()
                    .map(LivrablesPartielsDto::fromEntity)
                    .collect(Collectors.toList());
        }
        else
            return new ArrayList<>();
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesARefaireByAprrenant(Long id, Long idApp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            Collection<LivrablePartiel> livrablesPartielsList = new ArrayList<>();
            briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get().getLivrablePartiels().forEach(livrablePartiel -> {
                if (livrablePartiel.getLivrableRendu() != null && !livrablesPartielsList.contains(livrablePartiel) && Objects.equals(livrablePartiel.getLivrableRendu().getStatut(), "A Refaire"))
                    livrablesPartielsList.add(livrablePartiel);
            });
            return livrablesPartielsList.stream()
                    .map(LivrablesPartielsDto::fromEntity)
                    .collect(Collectors.toList());
        }
        else
            return new ArrayList<>();
    }

    @Override
    public Collection<LivrablesPartielsDto> findLivrablesValidesByAprrenant(Long id, Long idApp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            Collection<LivrablePartiel> livrablesPartielsList = new ArrayList<>();
            briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get().getLivrablePartiels().forEach(livrablePartiel -> {
                if (livrablePartiel.getLivrableRendu() != null && !livrablesPartielsList.contains(livrablePartiel) && Objects.equals(livrablePartiel.getLivrableRendu().getStatut(), "Valides"))
                    livrablesPartielsList.add(livrablePartiel);
            });
            return livrablesPartielsList.stream()
                    .map(LivrablesPartielsDto::fromEntity)
                    .collect(Collectors.toList());
        }
        else
            return new ArrayList<>();
    }

    @Override
    public LivrablesPartielsDto corrigerLivrable(Long id, String status) {
        String st = "";
        if (status.length() > 10 && status.startsWith("A Refaire", 1)){
            st = "A Refaire";
        }else if (status.length() > 8 && status.startsWith("Valides", 1)){
            st = "Valides";
        }
        if (livrablePartielRepository.findById(id).isPresent()){
            LivrablePartiel livrablePartiel = livrablePartielRepository.findById(id).get();
            livrablePartiel.getLivrableRendu().setStatut(st);
            livrablePartielRepository.flush();
            return LivrablesPartielsDto.fromEntity(livrablePartiel);
        }
        return null;
    }

    @Override
    public LivrablesPartielsDto deleteLivrable(Long id) {
        if (id != null && livrablePartielRepository.findById(id).isPresent()) {
           LivrablePartiel livrablePartiel = livrablePartielRepository.findById(id).get();
           livrablePartielRepository.findByArchiveFalseAndLibelleAndDescription(livrablePartiel.getLibelle(), livrablePartiel.getDescription()).forEach(l -> {
               l.setArchive(true);
               l.setBriefApprenant(null);
           });
           livrablePartielRepository.flush();
            return LivrablesPartielsDto.fromEntity(livrablePartiel);
        }
        return null;
    }

    @Override
    public Collection<GroupeDto> addApprenantsToBriefs(Long id, Collection<GroupeDto> groupeDto) {
        if (briefRepository.findById(id).isPresent()) {
            Brief brief = briefRepository.findById(id).get();

            groupeDto.forEach(g -> {
                if (g.getId() != null){
                    if (groupeRepository.findById(g.getId()).isPresent()){
                        if (!briefGroupeRepository.findByBriefIdAndGroupeId(brief.getId(), g.getId()).isPresent()) {
                            briefGroupeRepository.save(new BriefGroupe(groupeRepository.findById(g.getId()).get(), brief));
                            groupeRepository.findById(g.getId()).get().getApprenants().forEach(apprenant -> {
                                if (!briefApprenantRepository.findByBriefIdAndApprenantId(brief.getId(), apprenant.getId()).isPresent()) {
                                    briefApprenantRepository.save(new BriefApprenant(brief, apprenant));
                                }
                            });
                        }
                    }
                }else {
                    g.getApprenants().forEach(apprenantDto -> {
                        if (apprenantRepository.findById(apprenantDto.getId()).isPresent()) {
                            if (!briefApprenantRepository.findByBriefIdAndApprenantId(brief.getId(), apprenantDto.getId()).isPresent()) {
                                briefApprenantRepository.save(new BriefApprenant(brief, apprenantRepository.findById(apprenantDto.getId()).get()));
                            }
                        }
                    });
                }
            });
            brief.setStatut("En cours");
            briefRepository.flush();
            return groupeDto;
        }
        return new ArrayList<>();
    }

}
