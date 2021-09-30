package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("referentiel")
public interface ReferentielApi {

    @PostMapping("/referentiels")
    ReferentielDto save(
            @RequestParam String libelle,
            @RequestParam String description,
            @RequestParam String critereEvaluation,
            @RequestParam String critereAdmission,
            @RequestParam MultipartFile programme,
            @RequestParam String grpCompetences
    ) throws IOException;

    @GetMapping("/referentiels")
    List<ReferentielDto> findAll();

    @GetMapping("/admin/referentiels/{id}/groupe_competences")
    List<GroupeCompetenceDto> findGroupeCompetences(@PathVariable Long id);

    @GetMapping("/admin/referentiels/{id_referentiel}/groupe_competences/{id_groupeCompetence}")
    GroupeCompetenceDto findOneGroupeCompetence(@PathVariable Long id_referentiel, @PathVariable Long id_groupeCompetence);

    @GetMapping("/referentiels/{id}")
    ReferentielDto findById(@PathVariable Long id);

    @PutMapping("/referentiels/{id}")
    ReferentielDto put(
            @PathVariable Long id,
            @RequestParam String libelle,
            @RequestParam String description,
            @RequestParam String critereEvaluation,
            @RequestParam String critereAdmission,
            @RequestParam MultipartFile programme,
            @RequestParam String grpCompetences
    ) throws IOException;

    @DeleteMapping("/referentiels/{id}")
    void delete(@PathVariable Long id);
}
