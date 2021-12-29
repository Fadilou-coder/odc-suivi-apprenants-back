package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Api("role")
public interface RoleApi {

    @PostMapping("/roles/create")
    RoleDto save(@RequestBody RoleDto roleDto);

    @GetMapping("/roles")
    List<RoleDto> findAll(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy);

    @GetMapping("/roles/{id}")
    RoleDto findById(@PathVariable Long id);

    @DeleteMapping("/roles/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/roles/{id}")
    RoleDto put(@PathVariable Long id);

    @GetMapping("roles/{id}/admins")
    List<AdminDto> findAdminsByRole(@PathVariable Long id);

}
