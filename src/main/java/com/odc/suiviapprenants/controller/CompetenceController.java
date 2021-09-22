package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.CompetenceApi;
import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.service.CompetenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CompetenceController implements CompetenceApi {
    private CompetenceService competenceService;

    @Override
    public CompetenceDto save(CompetenceDto competenceDto) {
       return competenceService.save(competenceDto);
    }

    @Override
    public List<CompetenceDto> findAll() {
        return competenceService.findAll();
    }

    @Override
    public CompetenceDto findById(Long id) {
        return competenceService.findById(id);
    }

    @Override
    public void delete(Long id) {competenceService.delete(id);}

    @Override
    public CompetenceDto put(CompetenceDto competenceDto, Long id) {
        return competenceService.put(competenceDto,id);
    }

    @Override
    public CompetenceDto affectedNiveau(CompetenceDto competenceDto) {
        return competenceService.affectedNiveau(competenceDto);
    }
}
