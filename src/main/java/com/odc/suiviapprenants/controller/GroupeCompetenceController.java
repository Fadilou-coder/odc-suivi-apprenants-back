package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.GroupeCompetenceApi;
import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.service.GroupeCompetenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GroupeCompetenceController implements GroupeCompetenceApi {
    GroupeCompetenceService groupeCompetenceService;

    @Override
    public List<GroupeCompetenceDto> findAll() {
        return groupeCompetenceService.findAll();
    }

    @Override
    public List<CompetenceDto> findCompetences(Long id) {
        return groupeCompetenceService.findCompetences(id);
    }

    @Override
    public GroupeCompetenceDto save(GroupeCompetenceDto groupeCompetenceDto) {
        return groupeCompetenceService.save(groupeCompetenceDto);
    }

    @Override
    public GroupeCompetenceDto findById(Long id) {
        return groupeCompetenceService.findById(id);
    }

    @Override
    public GroupeCompetenceDto put(Long id, GroupeCompetenceDto groupeCompetenceDto) {
        return groupeCompetenceService.edit(id, groupeCompetenceDto);
    }

    @Override
    public void delete(Long id) {
        groupeCompetenceService.delete(id);
    }
}
