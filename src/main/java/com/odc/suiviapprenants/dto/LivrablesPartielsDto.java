package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.LivrablePartiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class LivrablesPartielsDto {

    Long id;
    private String libelle;
    private String description;
    private LocalDate delai;
    private String type;
    private LivrablesRendusDto livrableRendu;
    @JsonIgnore
    private BriefApprenantDto briefApprenant;
    private List<ApprenantDto> apprenants;

    public static LivrablesPartielsDto fromEntity(LivrablePartiel livrablePartiel){
        if (livrablePartiel == null) return null;
        return LivrablesPartielsDto.builder()
                .id(livrablePartiel.getId())
                .libelle(livrablePartiel.getLibelle())
                .description(livrablePartiel.getDescription())
                .delai(livrablePartiel.getDelai())
                .type(livrablePartiel.getType())
                .livrableRendu(LivrablesRendusDto.fromEntity(livrablePartiel.getLivrableRendu()))
                .briefApprenant(BriefApprenantDto.fromEntity(livrablePartiel.getBriefApprenant()))
                .build();
    }

    public static LivrablePartiel toEntity(LivrablesPartielsDto livrablesPartielsDto){
        if (livrablesPartielsDto == null) return null;
        LivrablePartiel livrablePartiel = new LivrablePartiel();
        livrablePartiel.setId(livrablesPartielsDto.getId());
        livrablePartiel.setLibelle(livrablesPartielsDto.getLibelle());
        livrablePartiel.setDelai(livrablesPartielsDto.getDelai());
        livrablePartiel.setDescription(livrablesPartielsDto.getDescription());
        livrablePartiel.setType(livrablesPartielsDto.getType());
        if (livrablesPartielsDto.getLivrableRendu() != null)
        livrablePartiel.setLivrableRendu(LivrablesRendusDto.toEntity(livrablesPartielsDto.getLivrableRendu()));
        if (livrablesPartielsDto.getBriefApprenant() != null)
        livrablePartiel.setBriefApprenant(BriefApprenantDto.toEntity(livrablesPartielsDto.getBriefApprenant()));

        return livrablePartiel;
    }
}
