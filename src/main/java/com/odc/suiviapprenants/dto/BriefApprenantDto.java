package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.BriefApprenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class BriefApprenantDto {

    private Long id;
    @JsonIgnore
    private BriefDto brief;
    private ApprenantDto apprenant;
    @JsonIgnore
    private Collection<LivrablesPartielsDto> livrablePartiels;
    private FilDeDiscutionDto filDiscussion;
    private Collection<LivrablesDto> livrables;
    private boolean valide;

    public static BriefApprenantDto fromEntity(BriefApprenant briefApprenant){
        if (briefApprenant == null) return null;
        return BriefApprenantDto.builder()
                .id(briefApprenant.getId())
                .apprenant(ApprenantDto.fromEntity(briefApprenant.getApprenant()))
                .filDiscussion(FilDeDiscutionDto.fromEntity(briefApprenant.getFilDiscussion()))
                .livrables(
                        briefApprenant.getLivrables() != null ?
                                briefApprenant.getLivrables().stream()
                                        .map(LivrablesDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .valide(briefApprenant.isValide())
                .build();
    }

    public static BriefApprenant toEntity(BriefApprenantDto briefApprenantDto){
        if (briefApprenantDto == null) return null;
        BriefApprenant briefApprenant = new BriefApprenant();
        briefApprenant.setId(briefApprenantDto.getId());
        briefApprenant.setApprenant(ApprenantDto.toEntity(briefApprenantDto.getApprenant()));
        briefApprenant.setBrief(BriefDto.toEntity(briefApprenantDto.getBrief()));
        briefApprenant.setFilDiscussion(FilDeDiscutionDto.toEntity(briefApprenantDto.getFilDiscussion()));
        briefApprenant.setValide(briefApprenantDto.isValide());
        if (briefApprenantDto.getLivrablePartiels() != null)
            briefApprenant.setLivrablePartiels(briefApprenantDto.getLivrablePartiels().stream().map(LivrablesPartielsDto::toEntity).collect(Collectors.toList()));
        if (briefApprenant.getLivrables() != null)
            briefApprenant.setLivrables(briefApprenantDto.getLivrables().stream().map(LivrablesDto::toEntity).collect(Collectors.toList()));
        return briefApprenant;
    }
}
