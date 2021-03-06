package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import java.io.IOException;
import java.util.List;

public interface GroupeService {
    GroupeDto save(GroupeDto groupeDto) throws IOException;

    List<GroupeDto> findAll();

    GroupeDto findById(Long id);

    List<ApprenantDto> findByApprenantById(Long id);

    GroupeDto put(Long id, GroupeDto groupeDto);

    void delete(Long id);

    List<ApprenantDto> findApprenantNonAffecterByGroupe(Long id);

    List<GroupeDto> findByFormateur(Long id);

    List<GroupeDto> findGroupeByFormateur(Long id);

    List<GroupeDto> findByFormateurAndBrief(Long idBr);

}
