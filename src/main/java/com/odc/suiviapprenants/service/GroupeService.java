package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.GroupeDto;
import java.io.IOException;
import java.util.List;

public interface GroupeService {
    GroupeDto save(GroupeDto groupeDto) throws IOException;

    List<GroupeDto> findAll();

    GroupeDto findById(Long id);

    GroupeDto put(Long id, GroupeDto groupeDto);

    void delete(Long id);
}
