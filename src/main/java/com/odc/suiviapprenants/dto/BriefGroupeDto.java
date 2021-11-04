package com.odc.suiviapprenants.dto;

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
    private BriefDto brief;
    private boolean valide;

    public static BriefGroupeDto fromEntity(BriefGroupe briefGroupe){
        if (briefGroupe == null) return null;
        return BriefGroupeDto.builder()
                .id(briefGroupe.getId())
                .groupe(GroupeDto.fromEntity(briefGroupe.getGroupe()))
                .brief(BriefDto.fromEntity(briefGroupe.getBrief()))
                .valide(briefGroupe.isValide())
                .build();
    }

    public static BriefGroupe toEntity(BriefGroupeDto briefGroupeDto){
        if (briefGroupeDto == null) return null;
        BriefGroupe briefGroupe = new BriefGroupe();
        briefGroupe.setGroupe(GroupeDto.toEntity(briefGroupeDto.getGroupe()));
        briefGroupe.setBrief(BriefDto.toEntity(briefGroupeDto.getBrief()));
        briefGroupe.setValide(briefGroupeDto.isValide());

        return briefGroupe;
    }
}
