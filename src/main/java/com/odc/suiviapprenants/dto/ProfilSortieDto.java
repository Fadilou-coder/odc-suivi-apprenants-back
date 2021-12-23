package com.odc.suiviapprenants.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.ProfilSortie;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProfilSortieDto {

    private Long id;

    private String libelle;

    @JsonIgnore
    private PromoDto promo;

    public static ProfilSortieDto fromEntity(ProfilSortie profilSortie){
        if (profilSortie == null) return null;
        return ProfilSortieDto.builder()
                .id(profilSortie.getId())
                .libelle(profilSortie.getLibelle())
                .build();
    }

    public static ProfilSortie toEntity(ProfilSortieDto profilSortieDto){

        ProfilSortie profilSortie = new ProfilSortie();
        profilSortie.setId(profilSortieDto.getId());
        profilSortie.setLibelle(profilSortieDto.getLibelle());

        return profilSortie;
    }
}
