package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.BriefCompetence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class BriefCompetenceDto {

    private Long id;
    @JsonIgnore
    private BriefDto brief;
    private CompetenceDto competence;
    private NiveauEvaluationDto niveau;
    private boolean valide;

    public static BriefCompetenceDto fromEntity(BriefCompetence briefCompetence){
        if (briefCompetence == null) return null;
        return BriefCompetenceDto.builder()
                .id(briefCompetence.getId())
                //.brief(BriefDto.fromEntity(briefCompetence.getBrief()))
                .competence(CompetenceDto.fromEntity(briefCompetence.getCompetence()))
                .niveau(NiveauEvaluationDto.fromEntity(briefCompetence.getNiveau()))
                .valide(briefCompetence.isValide())
                .build();
    }

    public static BriefCompetence toEntity(BriefCompetenceDto briefCompetenceDto){
        if (briefCompetenceDto == null) return null;
        BriefCompetence briefCompetence = new BriefCompetence();
        briefCompetence.setId(briefCompetenceDto.getId());
        briefCompetence.setCompetence(CompetenceDto.toEntity(briefCompetenceDto.getCompetence()));
        briefCompetence.setBrief(BriefDto.toEntity(briefCompetenceDto.getBrief()));
        briefCompetence.setNiveau(NiveauEvaluationDto.toEntity(briefCompetenceDto.getNiveau()));
        briefCompetence.setValide(briefCompetenceDto.isValide());

        return briefCompetence;
    }
}
