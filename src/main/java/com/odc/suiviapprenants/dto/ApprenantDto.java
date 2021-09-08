package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Apprenant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ApprenantDto {
    private Long id;

    protected String username;

    protected String password;

    protected String prenom;

    protected String nom;

    protected String email;

    protected String cni;

    protected String adresse;

    protected String numeroTelephone;

    protected LocalDate dateNaissance;

    protected byte[] avatar;

    private String role;

    public static ApprenantDto fromEntity(Apprenant apprenant){
        if (apprenant == null){
            return null;
        }
        return ApprenantDto.builder()
                .id(apprenant.getId())
                .username(apprenant.getUsername())
                .password(apprenant.getPassword())
                .prenom(apprenant.getPrenom())
                .nom(apprenant.getNom())
                .email(apprenant.getEmail())
                .cni(apprenant.getCni())
                .adresse(apprenant.getAdresse())
                .numeroTelephone(apprenant.getNumeroTelephone())
                .dateNaissance(apprenant.getDateNaissance())
                .avatar(apprenant.getAvatar())
                .role(apprenant.getRole())
                .build();
    }

    public static Apprenant toEntity(ApprenantDto apprenantDto){
        if (apprenantDto == null) return null;

        Apprenant apprenant = new Apprenant();
        apprenant.setId(apprenantDto.getId());
        apprenant.setUsername(apprenantDto.getUsername());
        apprenant.setPassword(apprenantDto.getPassword());
        apprenant.setPrenom(apprenantDto.getPrenom());
        apprenant.setNom(apprenantDto.getNom());
        apprenant.setEmail(apprenantDto.getEmail());
        apprenant.setCni(apprenantDto.getCni());
        apprenant.setAdresse(apprenantDto.getAdresse());
        apprenant.setNumeroTelephone(apprenantDto.getNumeroTelephone());
        apprenant.setDateNaissance(apprenantDto.getDateNaissance());
        apprenant.setAvatar(apprenantDto.getAvatar());
        apprenant.setRole(apprenantDto.getRole());

        return apprenant;

    }
}
