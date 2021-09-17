package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.Referentiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@Builder
public class NiveauEvaluationDto {
    @Id
    private Long id;

    private String libelle;

    private String groupeAction;

    private String critereEvaluation;

    private ReferentielDto referentiel;

    @JsonIgnore
    private CompetenceDto competence;

    public static NiveauEvaluationDto fromEntity(NiveauEvaluation niveauEvaluation)
    {
        if(niveauEvaluation == null){
            return null;
        }
        return   NiveauEvaluationDto.builder()
                .id(niveauEvaluation.getId())
                .libelle(niveauEvaluation.getLibelle())
                .groupeAction(niveauEvaluation.getGroupeAction())
                .critereEvaluation(niveauEvaluation.getCritereEvaluation())
                .referentiel(
                        ReferentielDto.builder()
                                .id(niveauEvaluation.getReferentiel().getId())
                                .libelle(niveauEvaluation.getReferentiel().getLibelle())
                                .build()
                )
                .build();
    }

    public static  NiveauEvaluation toEntity(NiveauEvaluationDto niveauEvaluationDto)
    {
        if(niveauEvaluationDto == null){
            return null;
        }
        NiveauEvaluation niveauEvaluation = new NiveauEvaluation();
        niveauEvaluation.setId(niveauEvaluationDto.getId());
        niveauEvaluation.setLibelle(niveauEvaluationDto.getLibelle());
        niveauEvaluation.setGroupeAction(niveauEvaluationDto.getGroupeAction());
        niveauEvaluation.setCritereEvaluation(niveauEvaluationDto.getCritereEvaluation());
        niveauEvaluation.setReferentiel(ReferentielDto.toEntity(niveauEvaluationDto.getReferentiel()));
        return niveauEvaluation;
    }
}
