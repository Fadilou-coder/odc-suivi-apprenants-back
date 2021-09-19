package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.ProfilSortieDto;

import java.util.List;

public interface ProfileSortieService {

    ProfilSortieDto save(ProfilSortieDto profilSortieDto);

    List<ProfilSortieDto> findAll();

    ProfilSortieDto findById(Long id);

    void delete(Long id);

    ProfilSortieDto put(ProfilSortieDto profilSortieDto,Long id);
}
