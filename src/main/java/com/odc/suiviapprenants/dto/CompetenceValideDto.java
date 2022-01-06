package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.CompetenceValide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceValideDto {

    Long id;

    private NiveauEvaluationDto niveauEvaluation;

    private CompetenceDto competence;

    private ApprenantDto apprenant;

    public static CompetenceValideDto fromEntity(CompetenceValide competenceValide){
        if (competenceValide == null) return null;
        return CompetenceValideDto.builder()
                .id(competenceValide.getId())
                .niveauEvaluation(NiveauEvaluationDto.fromEntity(competenceValide.getNiveau()))
                .competence(CompetenceDto.fromEntity(competenceValide.getCompetence()))
                .apprenant(ApprenantDto.fromEntity(competenceValide.getApprenant()))
                .build();
    }

    public static CompetenceValide toEntity(CompetenceValideDto competenceValideDto){
        if (competenceValideDto == null) return null;
        CompetenceValide competenceValide = new CompetenceValide();
        competenceValide.setId(competenceValideDto.getId());
        competenceValide.setNiveau(NiveauEvaluationDto.toEntity(competenceValideDto.getNiveauEvaluation()));
        competenceValide.setCompetence(CompetenceDto.toEntity(competenceValideDto.getCompetence()));
        competenceValide.setApprenant(ApprenantDto.toEntity(competenceValideDto.getApprenant()));

        return  competenceValide;
    }
}
