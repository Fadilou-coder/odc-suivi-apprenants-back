package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.RoleApi;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RoleController implements RoleApi {
    RoleService roleService;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Override
    public List<RoleDto> findAll(Optional<Integer> page, Optional<String> sortBy) {
        return roleService.findAll(page, sortBy).stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
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
