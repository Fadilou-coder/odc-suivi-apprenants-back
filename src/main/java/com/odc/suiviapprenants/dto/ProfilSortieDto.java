package com.odc.suiviapprenants.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.ProfilSortie;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
public class ProfilSortieDto {

    private Long id;

    private String libelle;

    @JsonIgnore
    private PromoDto promo;

    @JsonIgnore
    private List<ApprenantDto> apprenant;

    public static ProfilSortieDto fromEntity(ProfilSortie profilSortie){
        if (profilSortie == null) return null;
        return ProfilSortieDto.builder()
                .id(profilSortie.getId())
                .libelle(profilSortie.getLibelle())
                .apprenant(
                        profilSortie.getApprenants() !=null
                        ? profilSortie.getApprenants().stream().map(ApprenantDto::fromEntity).collect(Collectors.toList()) : null
                )
                .build();
    }

    public static ProfilSortie toEntity(ProfilSortieDto profilSortieDto){

        ProfilSortie profilSortie = new ProfilSortie();
        profilSortie.setId(profilSortieDto.getId());
        profilSortie.setLibelle(profilSortieDto.getLibelle());
        if(profilSortieDto.getApprenant() !=null)
            profilSortie.setApprenants(profilSortieDto.getApprenant().stream().map(ApprenantDto::toEntity).collect(Collectors.toList()));
        return profilSortie;
    }
}
