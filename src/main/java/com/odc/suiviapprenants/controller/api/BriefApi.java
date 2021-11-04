package com.odc.suiviapprenants.controller.api;


import com.odc.suiviapprenants.dto.BriefDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.io.IOException;
import java.time.LocalDate;
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
            @RequestParam("tags") String tags,
            @RequestParam("groupes") String groupes,
            @RequestParam("apprenants") String apprenants,
            @RequestParam("competences") String competences,
            @RequestParam("niveaux") String niveaux
    ) throws IOException;

    @GetMapping("/briefs/{id}")
    BriefDto findBriefById(@PathVariable Long id);
}
