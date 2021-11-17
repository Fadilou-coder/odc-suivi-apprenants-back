package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Livrable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class LivrablesDto {

    Long id;
    String url;
    BriefApprenantDto briefApprenant;
    LivrablesAttendusDto livrableAttendu;


    public static LivrablesDto fromEntity(Livrable livrable){
        if (livrable == null) return null;
        return LivrablesDto.builder()
                .id(livrable.getId())
                .url(livrable.getUrl())
                .livrableAttendu(LivrablesAttendusDto.fromEntity(livrable.getLivrableAttendu()))
                .build();
    }

    public static Livrable toEntity(LivrablesDto livrablesDto){
        if (livrablesDto == null) return null;
        Livrable livrable = new Livrable();
        livrable.setId(livrablesDto.getId());
        livrable.setUrl(livrablesDto.getUrl());
        livrable.setBriefApprenant(BriefApprenantDto.toEntity(livrablesDto.getBriefApprenant()));
        livrable.setLivrableAttendu(LivrablesAttendusDto.toEntity(livrablesDto.getLivrableAttendu()));
        return livrable;
    }
}
