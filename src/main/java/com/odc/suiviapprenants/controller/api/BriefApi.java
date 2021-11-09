package com.odc.suiviapprenants.controller.api;


import com.odc.suiviapprenants.dto.BriefDto;
import com.odc.suiviapprenants.dto.LivrablesAttendusDto;
import com.odc.suiviapprenants.dto.LivrablesPartielsDto;
import com.odc.suiviapprenants.dto.LivrablesRendusDto;
import io.swagger.annotations.Api;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.io.IOException;
import java.time.LocalDate;
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

    @PostMapping("/briefs/{id}/livrablesPartiels")
    LivrablesPartielsDto addLivrablesPartiels(@RequestBody LivrablesPartielsDto livrablesPartielsDto, @PathVariable Long id);

    @GetMapping("/briefs/{id}/livrablesPartiels")
    Collection<LivrablesPartielsDto> ListLivrablesPartiels(@PathVariable Long id);

    @PostMapping("/briefs/{id}/apprenants/{idApp}/livrables")
    Collection<LivrablesAttendusDto> addUrl(@RequestBody Collection<LivrablesAttendusDto> livrablesAttendusDtos, @PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @GetMapping("/briefs/{id}/apprenants/{idApp}/livrablesPartiels")
    Collection<LivrablesPartielsDto> findLivrablesPartielsByAprrenant(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PutMapping("/briefs/{id}/apprenants/{idApp}/livrablesPartiels/{idLp}")
    LivrablesPartielsDto rendreLivrablePartiel(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp, @PathVariable("idLp") Long idLp);

    @PutMapping ("/briefs/{id}")
    BriefDto cloturerBrief(@PathVariable Long id);

}
