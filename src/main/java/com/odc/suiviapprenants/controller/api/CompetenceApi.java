package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.ProfilSortieDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("competence")
public interface CompetenceApi {

    @PostMapping("competence/create")
    CompetenceDto save(@RequestBody CompetenceDto competenceDto);

    @GetMapping("/competence")
    List<CompetenceDto> findAll();

    @GetMapping("/competence/{id}")
    CompetenceDto findById(@PathVariable Long id);

    @DeleteMapping("/competence/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/competence/{id}")
    CompetenceDto put(@RequestBody CompetenceDto competenceDto ,@PathVariable Long id);
}
