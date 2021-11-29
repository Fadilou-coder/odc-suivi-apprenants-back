package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Apprenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class ApprenantDto {
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String prenom;

    private String nom;

    private String email;

    private String cni;

    private String adresse;

    private String numeroTelephone;

    private LocalDate dateNaissance;

    private byte[] avatar;

    private String role;

    private String etat;

    private String matricule;


    public static ApprenantDto fromEntity(Apprenant apprenant){
        if (apprenant == null){
            return null;
        }
        return ApprenantDto.builder()
                .id(apprenant.getId())
                .username(apprenant.getUsername())
                .prenom(apprenant.getPrenom())
                .nom(apprenant.getNom())
                .email(apprenant.getEmail())
                .cni(apprenant.getCni())
                .adresse(apprenant.getAdresse())
                .numeroTelephone(apprenant.getNumeroTelephone())
                .dateNaissance(apprenant.getDateNaissance())
                .avatar(apprenant.getAvatar())
                .role(apprenant.getRole())
                .etat(apprenant.getEtat())
                .matricule(apprenant.getMatricule())
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
        apprenant.setEtat(apprenantDto.getEtat());
        apprenant.setMatricule(apprenantDto.getMatricule());

        return apprenant;

    }
}
