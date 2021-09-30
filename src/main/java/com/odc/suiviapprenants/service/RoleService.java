package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.model.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    Page<Role> findAll(Optional<Integer> page, Optional<String> sortBy);

    RoleDto findById(Long id);

    void delete(Long id);

    List<AdminDto> findAdminsByRole(Long id);

}
