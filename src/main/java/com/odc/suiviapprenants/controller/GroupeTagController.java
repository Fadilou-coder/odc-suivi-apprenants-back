package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.GroupeTagApi;
import com.odc.suiviapprenants.dto.GroupeTagDto;
import com.odc.suiviapprenants.service.GroupeTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class GroupeTagController implements GroupeTagApi {
    private GroupeTagService groupeTagService;

    @Override
    public GroupeTagDto save(GroupeTagDto groupeTagDto) throws IOException {
        return groupeTagService.save(groupeTagDto);
    }

    @Override
    public List<GroupeTagDto> findAll() {
        return groupeTagService.findAll();
    }

    @Override
    public GroupeTagDto findById(Long id) {
        return groupeTagService.findById(id);
    }

    @Override
    public void delete(Long id) {
       groupeTagService.delete(id);
    }

    @Override
    public GroupeTagDto put(Long id, GroupeTagDto groupeTagDto) {
        return groupeTagService.put(id, groupeTagDto);
    }
}
