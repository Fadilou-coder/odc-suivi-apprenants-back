package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;

import java.util.List;

public interface GroupeCompetenceService {
    List<GroupeCompetenceDto> findAll();

    List<CompetenceDto> findCompetences(Long id);

    GroupeCompetenceDto save(GroupeCompetenceDto groupeCompetenceDto);

    GroupeCompetenceDto findById(Long id);

    void delete(Long id);

    GroupeCompetenceDto edit(Long id, GroupeCompetenceDto groupeCompetenceDto);
}
