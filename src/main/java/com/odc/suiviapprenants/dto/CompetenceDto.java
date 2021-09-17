package com.odc.suiviapprenants.dto;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import java.util.*;
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
        //   competence.setNiveauEvaluations(competenceDto.getNiveauEvaluations().stream().map(NiveauEvaluationDto::toEntity).collect(Collectors.toList()));
        return competence;
    }
}
