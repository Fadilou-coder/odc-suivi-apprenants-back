package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.GroupeCompetenceDto;

import java.util.List;

public interface GroupeCompetenceService {
    List<GroupeCompetenceDto> findAll();

    GroupeCompetenceDto save(GroupeCompetenceDto groupeCompetenceDto);

    GroupeCompetenceDto findById(Long id);

    void delete(Long id);

    GroupeCompetenceDto edit(GroupeCompetenceDto groupeCompetenceDto);
}
