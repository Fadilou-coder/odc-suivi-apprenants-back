package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    List<RoleDto> findAll();

    RoleDto findById(Long id);

    void delete(Long id);

    List<AdminDto> findAdminsByRole(Long id);

}
