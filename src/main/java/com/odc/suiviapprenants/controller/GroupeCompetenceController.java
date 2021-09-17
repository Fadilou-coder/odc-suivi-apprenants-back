package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.GroupeCompetenceApi;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.service.GroupeCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupeCompetenceController implements GroupeCompetenceApi {

    @Autowired
    GroupeCompetenceService groupeCompetenceService;

    @Override
    public List<GroupeCompetenceDto> findAll() {
        return groupeCompetenceService.findAll();
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
    public GroupeCompetenceDto put(GroupeCompetenceDto groupeCompetenceDto) {
        return groupeCompetenceService.edit(groupeCompetenceDto);
    }

    @Override
    public void delete(Long id) {
        groupeCompetenceService.delete(id);
    }
}
