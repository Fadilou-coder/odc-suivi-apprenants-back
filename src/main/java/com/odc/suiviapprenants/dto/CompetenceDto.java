package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Competence;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import javax.persistence.Id;
import java.util.stream.Collectors;

@Data
@Builder
public class CompetenceDto {
    @Id
    private Long id;
    private String libelle;

    private List<NiveauEvaluationDto> niveauEvaluations;

    public static CompetenceDto fromEntity(Competence competence)
    {
        if(competence == null){
            return null;
        }

        return  CompetenceDto.builder()
                .id(competence.getId())
                .libelle(competence.getLibelle())
                .niveauEvaluations(
                        competence.getNiveauEvaluations() == null
                                ? null
                                : competence.getNiveauEvaluations().stream().map(NiveauEvaluationDto::fromEntity).collect(Collectors.toList())
                )

                .build();
    }

    public static  Competence toEntity(CompetenceDto competenceDto)
    {
        if(competenceDto == null){
            return null;
        }
        Competence competence = new Competence();
        competence.setId(competenceDto.getId());
        competence.setLibelle(competenceDto.getLibelle());
        return competence;
    }
}
