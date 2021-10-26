package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.GroupeTagDto;
import com.odc.suiviapprenants.dto.TagDto;

import java.io.IOException;
import java.util.List;

public interface GroupeTagService {


    GroupeTagDto save(GroupeTagDto groupeTagDto) throws IOException;

    List<GroupeTagDto> findAll();

    GroupeTagDto findById(Long id);

    GroupeTagDto put(Long id, GroupeTagDto groupeTagDto);

    void delete(Long id);

}
