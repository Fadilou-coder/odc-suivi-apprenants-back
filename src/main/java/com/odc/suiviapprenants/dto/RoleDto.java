package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleDto {

    private Long id;
    private String libelle;

    @JsonIgnore
    private List<AdminDto> admin;

    public static RoleDto fromEntity(Role role){
        if (role == null) return null;

        return RoleDto.builder()
                .id(role.getId())
                .libelle(role.getLibelle())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if (roleDto == null) return null;
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setLibelle(roleDto.getLibelle());

       // role.setAdmins(roleDto.getAdmin().stream().map(AdminDto::toEntity).collect(Collectors.toList()));

        return role;
    }
}
