package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.ProfileSortieApi;
import com.odc.suiviapprenants.dto.ProfilSortieDto;
import com.odc.suiviapprenants.service.ProfileSortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileSortieController implements ProfileSortieApi {

    @Autowired
    ProfileSortieService profileSortieService;

    @Override
    public ProfilSortieDto save(ProfilSortieDto profilSortieDto) {return profileSortieService.save(profilSortieDto);}

    @Override
    public List<ProfilSortieDto> findAll() {return profileSortieService.findAll();}

    @Override
    public ProfilSortieDto findById(Long id) {return profileSortieService.findById(id);}

    @Override
    public void delete(Long id) {profileSortieService.delete(id);}

    @Override
    public ProfilSortieDto put(ProfilSortieDto profilSortieDto,Long id) {return profileSortieService.put(profilSortieDto,id);}

}
