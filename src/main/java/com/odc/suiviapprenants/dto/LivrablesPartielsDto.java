package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.LivrablePartiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
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
    private Collection<LivrablesRendusDto> livrableRendus;
    private Collection<BriefApprenantDto> briefApprenant;

    public static LivrablesPartielsDto fromEntity(LivrablePartiel livrablePartiel){
        if (livrablePartiel == null) return null;
        return LivrablesPartielsDto.builder()
                .id(livrablePartiel.getId())
                .libelle(livrablePartiel.getLibelle())
                .delai(livrablePartiel.getDelai())
                .type(livrablePartiel.getType())
                .livrableRendus(
                        livrablePartiel.getLivrableRendus() != null ?
                                livrablePartiel.getLivrableRendus().stream()
                                        .map(LivrablesRendusDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .briefApprenant(
                        livrablePartiel.getBriefApprenants() != null ?
                                livrablePartiel.getBriefApprenants().stream()
                                        .map(BriefApprenantDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
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
        if (livrablesPartielsDto.getLivrableRendus() != null)
        livrablePartiel.setLivrableRendus(livrablesPartielsDto.getLivrableRendus().stream().map(LivrablesRendusDto::toEntity).collect(Collectors.toList()));
        if (livrablesPartielsDto.getBriefApprenant() != null)
        livrablePartiel.setBriefApprenants(livrablesPartielsDto.getBriefApprenant().stream().map(BriefApprenantDto::toEntity).collect(Collectors.toList()));
        return livrablePartiel;
    }
}
