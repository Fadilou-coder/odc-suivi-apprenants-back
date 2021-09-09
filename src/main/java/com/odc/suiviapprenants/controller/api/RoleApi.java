package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.RoleDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("role")
public interface RoleApi {

    @PostMapping("/roles/create")
    RoleDto save(@RequestBody RoleDto roleDto);

    @GetMapping("/roles")
    List<RoleDto> findAll();

    @GetMapping("/roles/{id}")
    RoleDto findById(@PathVariable Long id);

    @DeleteMapping("/roles/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/roles/{id}")
    RoleDto put(@PathVariable Long id);

}
