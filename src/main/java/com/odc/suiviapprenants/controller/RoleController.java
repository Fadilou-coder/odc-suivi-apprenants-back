package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.RoleApi;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {
    RoleService roleService;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Override
    public RoleDto findById(Long id) {
        return roleService.findById(id);
    }

    @Override
    public void delete(Long id) {
        roleService.delete(id);
    }

    @Override
    public RoleDto put(Long id) {
        return null;
    }

    @Override
    public List<AdminDto> findAdminsByRole(Long id) {
        return roleService.findAdminsByRole(id);
    }
}
