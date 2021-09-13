package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odc.suiviapprenants.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;

    protected String username;

    protected String password;

    protected String prenom;

    protected String nom;

    protected String email;

    protected String cni;

    protected String adresse;

    protected String numeroTelephone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dateNaissance;

    @Lob
    protected byte[] avatar;

    private RoleDto role;

    public static AdminDto fromEntity(Admin admin){
        if (admin == null){
            return null;
        }
        return AdminDto.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .prenom(admin.getPrenom())
                .nom(admin.getNom())
                .email(admin.getEmail())
                .cni(admin.getCni())
                .adresse(admin.getAdresse())
                .numeroTelephone(admin.getNumeroTelephone())
                .dateNaissance(admin.getDateNaissance())
                .avatar(admin.getAvatar())
                .role(RoleDto.fromEntity(admin.getRole()))
                .build();
    }

    public static Admin toEntity(AdminDto adminDto){
        if (adminDto == null) return null;

        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setPrenom(adminDto.getPrenom());
        admin.setNom(adminDto.getNom());
        admin.setEmail(adminDto.getEmail());
        admin.setCni(adminDto.getCni());
        admin.setAdresse(adminDto.getAdresse());
        admin.setNumeroTelephone(adminDto.getNumeroTelephone());
        admin.setDateNaissance(adminDto.getDateNaissance());
        admin.setAvatar(adminDto.getAvatar());
        admin.setRole(RoleDto.toEntity(adminDto.getRole()));

        return admin;

    }

}
