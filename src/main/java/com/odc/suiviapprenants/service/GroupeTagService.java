package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.GroupeTagDto;
import com.odc.suiviapprenants.model.GroupeTag;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GroupeTagService {


    GroupeTagDto save(GroupeTagDto groupeTagDto) throws IOException;

    Page<GroupeTag> findAll(Optional<Integer> page,
                            Optional<String> sortBy);

    GroupeTagDto findById(Long id);

    GroupeTagDto put(Long id, GroupeTagDto groupeTagDto);

    void delete(Long id);

}
