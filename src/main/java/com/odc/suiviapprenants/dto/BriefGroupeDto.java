package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.BriefGroupe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class BriefGroupeDto {
    private Long id;
    private GroupeDto groupe;
    @JsonIgnore
    private BriefDto brief;
    private boolean valide;

    public static BriefGroupeDto fromEntity(BriefGroupe briefGroupe){
        if (briefGroupe == null) return null;
        return BriefGroupeDto.builder()
                .id(briefGroupe.getId())
                .groupe(GroupeDto.fromEntity(briefGroupe.getGroupe()))
                .valide(briefGroupe.isValide())
                .build();
    }

    public static BriefGroupe toEntity(BriefGroupeDto briefGroupeDto){
        if (briefGroupeDto == null) return null;
        BriefGroupe briefGroupe = new BriefGroupe();
        briefGroupe.setId(briefGroupeDto.getId());
        briefGroupe.setGroupe(GroupeDto.toEntity(briefGroupeDto.getGroupe()));
        briefGroupe.setBrief(BriefDto.toEntity(briefGroupeDto.getBrief()));
        briefGroupe.setValide(briefGroupeDto.isValide());

        return briefGroupe;
    }
}
