package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.CompetenceDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("competence")
public interface CompetenceApi {

    @PostMapping("/competences/create")
    CompetenceDto save(@RequestBody CompetenceDto competenceDto);

    @GetMapping("/competences")
    List<CompetenceDto> findAll();

    @GetMapping("/competences/{id}")
    CompetenceDto findById(@PathVariable Long id);

    @DeleteMapping("/competences/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/competences/{id}")
    CompetenceDto put(@RequestBody CompetenceDto competenceDto ,@PathVariable Long id);

    @PutMapping("/competences/niveaux_affectes")
    CompetenceDto affectedNiveau(@RequestBody CompetenceDto competenceDto);
}
