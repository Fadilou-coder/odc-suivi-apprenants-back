package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.GroupeApi;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import com.odc.suiviapprenants.service.GroupeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class GroupeController implements GroupeApi {

    private GroupeService groupeService;

    @Override
    public GroupeDto save(GroupeDto groupeDto) throws IOException {
        return groupeService.save(groupeDto);
    }

    @Override
    public List<GroupeDto> findAll() {
        return groupeService.findAll();
    }

    @Override
    public GroupeDto findById(Long id) {
        return groupeService.findById(id);
    }

    @Override
    public List<ApprenantDto> findByApprenantById(Long id) {
        return groupeService.findByApprenantById(id);
    }

    @Override
    public void delete(Long id) {
         groupeService.delete(id);
    }

    @Override
    public GroupeDto put(GroupeDto groupeDto, Long id) {
        return groupeService.put(id, groupeDto);
    }

    @Override
    public List<ApprenantDto> findApprenantNonAffecterByGroupe(Long id) {
        return groupeService.findApprenantNonAffecterByGroupe(id);
    }
}
