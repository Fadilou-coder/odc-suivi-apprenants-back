package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Groupe Competence")
public interface GroupeCompetenceApi {

    @GetMapping("/groupe_competences")
    List<GroupeCompetenceDto> findAll();

    @GetMapping("/groupe_competences/{id}/competences")
    List<CompetenceDto> findCompetences(@PathVariable Long id);

    @PostMapping("/groupe_competences/create")
    GroupeCompetenceDto save(@RequestBody GroupeCompetenceDto groupeCompetenceDto);

    @GetMapping("/groupe_competences/{id}")
    GroupeCompetenceDto findById(@PathVariable Long id);

    @PutMapping("/groupe_competences/{id}")
    GroupeCompetenceDto put(@PathVariable Long id, @RequestBody GroupeCompetenceDto groupeCompetenceDto);

    @DeleteMapping("/groupe_competences/{id}")
    void delete(@PathVariable Long id);
}
