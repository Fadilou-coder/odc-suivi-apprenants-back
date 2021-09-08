package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.RoleApi;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

    @Autowired
    RoleService roleService;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return null;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Override
    public RoleDto findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public RoleDto put(Long id) {
        return null;
    }
}
