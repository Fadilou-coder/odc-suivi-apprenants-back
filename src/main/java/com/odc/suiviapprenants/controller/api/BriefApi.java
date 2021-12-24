package com.odc.suiviapprenants.controller.api;


import com.odc.suiviapprenants.dto.*;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Api("Brief")
public interface BriefApi {

    @GetMapping("/briefs")
    List<BriefDto> findAll();

    @PostMapping("/briefs/create")
    BriefDto save(
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("contexte") String contexte,
            @RequestParam("modalitePedagodiques") String modalitePedagodiques,
            @RequestParam("criterePerformances") String criterePerformances,
            @RequestParam("modaliteEvaluations") String modaliteEvaluations,
            @RequestParam("image") MultipartFile image,
            @RequestParam("tags") List<String> tags,
            @RequestParam("groupes") List<String> groupes,
            @RequestParam("apprenants") List<String> apprenants,
            @RequestParam("competences") List<String> competences,
            @RequestParam("niveaux") List<Long> niveaux
    ) throws Exception;

    @GetMapping("/briefs/{id}")
    BriefDto findBriefById(@PathVariable Long id);

    @GetMapping("/formateurs/{id}/briefs")
    List<BriefDto> findByFormateur(@PathVariable Long id);

    @GetMapping("/apprenants/{id}/briefs")
    List<BriefDto> findByApprenant(@PathVariable Long id);

    @PostMapping("/briefs/{id}")
    BriefDto duopliquerBrief(@PathVariable Long id);

    @PutMapping("/briefs/{id}")
    BriefDto putBrief(
            @PathVariable Long id,
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("contexte") String contexte,
            @RequestParam("modalitePedagodiques") String modalitePedagodiques,
            @RequestParam("criterePerformances") String criterePerformances,
            @RequestParam("modaliteEvaluations") String modaliteEvaluations,
            @RequestParam("image") MultipartFile image,
            @RequestParam("tags") List<String> tags,
            @RequestParam("competences") List<String> competences,
            @RequestParam("niveaux") List<Long> niveaux
    ) throws Exception;

    @PostMapping("/briefs/{id}/livrablesPartiels")
    LivrablesPartielsDto addLivrablesPartiels(@RequestBody LivrablesPartielsDto livrablesPartielsDto, @PathVariable Long id);

    @GetMapping("/briefs/{id}/livrablesPartiels")
    Collection<LivrablesPartielsDto> ListLivrablesPartiels(@PathVariable Long id);

    @PostMapping("/briefs/{id}/apprenants/{idApp}/livrables")
    Collection<LivrablesDto> addUrl(@RequestBody Collection<LivrablesDto> livrablesDtos, @PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @GetMapping ("/briefs/{id}/apprenants/{idApp}/livrables")
    Collection<LivrablesDto> findUrl(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @GetMapping("/briefs/{id}/apprenants/{idApp}/livrablesPartiels")
    Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PutMapping("/briefs/{id}/apprenants/{idApp}/livrablesPartiels/{idLp}")
    LivrablesPartielsDto rendreLivrablePartiel(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp, @PathVariable("idLp") Long idLp);

    @PutMapping ("/briefs/{id}/cloturer")
    BriefDto cloturerBrief(@PathVariable Long id);

    @DeleteMapping ("/briefs/{id}")
    BriefDto ArchiverBrief(@PathVariable Long id);

    @PostMapping("/appprenant/{id}/competence/{idComp}")
    CompetenceValideDto validerCompetence(@PathVariable("id") Long id, @PathVariable("idComp") Long idComp);

    @GetMapping("/briefs/{id}/apprenants/{idApp}/livrablesACorriger")
    Collection<LivrablesPartielsDto> findLivrablesACorrigerByAprrenant(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @GetMapping("/briefs/{id}/apprenants/{idApp}/livrablesARefaire")
    Collection<LivrablesPartielsDto> findLivrablesARefaireByAprrenant(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @GetMapping("/briefs/{id}/apprenants/{idApp}/livrablesValides")
    Collection<LivrablesPartielsDto> findLivrablesValidesByAprrenant(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PutMapping("livrablePartiel/{id}")
    LivrablesPartielsDto corrigerLivrable(@PathVariable Long id, @RequestBody String status);

    @DeleteMapping("livrablePartiel/{id}")
    LivrablesPartielsDto deleteLivrable(@PathVariable Long id);

    @PostMapping("/briefs/{id}/addAprrenants")
    Collection<GroupeDto> addApprenantsToBriefs(@PathVariable Long id, @RequestBody Collection<GroupeDto> groupeDto);

}
