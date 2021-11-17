package com.odc.suiviapprenants.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Livrable;
import com.odc.suiviapprenants.model.LivrableAttendu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class LivrablesAttendusDto {

    Long id;
    private String libelle;
    @JsonIgnore
    private Collection<LivrablesDto> livrables;

    public static LivrablesAttendusDto fromEntity(LivrableAttendu livrableAttendu){
        if (livrableAttendu == null) return null;
        return LivrablesAttendusDto.builder()
                .id(livrableAttendu.getId())
                .libelle(livrableAttendu.getLibelle())
                .build();
    }

    public static LivrableAttendu toEntity(LivrablesAttendusDto livrablesAttendusDto){
        if (livrablesAttendusDto == null) return null;
        LivrableAttendu livrableAttendu = new LivrableAttendu();
        livrableAttendu.setId(livrablesAttendusDto.getId());
        livrableAttendu.setLibelle(livrablesAttendusDto.getLibelle());
        if (livrablesAttendusDto.getLivrables() != null)
        livrableAttendu.setLivrables(livrablesAttendusDto.getLivrables().stream().map(LivrablesDto::toEntity).collect(Collectors.toList()));
        return livrableAttendu;
    }


}
