package com.odc.suiviapprenants.dto;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import java.util.*;

@Data
@Builder
public class CompetenceDto {
    @Id
    private Long id;
    private String libelle;

    private List<NiveauEvaluationDto> niveauEvaluations;

    public static CompetenceDto mapFromEntity(Competence competence)
    {
        //  ModelMapper modelMapper = new ModelMapper();
        if(competence == null){
            return null;
        }
        List<NiveauEvaluationDto> niveauEvaluationDtoList = new ArrayList<>();
        for (NiveauEvaluation niveauEvaluation: competence.getNiveauEvaluations())
        {
            NiveauEvaluationDto niveauEvaluationDto = NiveauEvaluationDto.builder()
                    .id(niveauEvaluation.getId())
                    .libelle(niveauEvaluation.getLibelle())
                    .critereEvaluation(niveauEvaluation.getCritereEvaluation())
                    .groupeAction(niveauEvaluation.getGroupeAction()).build();
            niveauEvaluationDtoList.add(niveauEvaluationDto);
        }
        return  CompetenceDto.builder()
                .id(competence.getId())
                .libelle(competence.getLibelle())
                .niveauEvaluations(niveauEvaluationDtoList)
                .build();


        // return modelMapper.map(competence,CompetenceDto.class);
    }

    public static  Competence mapToEntity(CompetenceDto competenceDto)
    {
        ModelMapper modelMapper = new ModelMapper();
        if(competenceDto == null){
            return null;
        }
        Competence competence = new Competence();
        competence.setId(competenceDto.getId());
        competence.setLibelle(competenceDto.getLibelle());
        List<NiveauEvaluation> niveauEvaluationList = new ArrayList<>();
        for (NiveauEvaluationDto niveauEvaluationDto : competenceDto.getNiveauEvaluations()) {
            NiveauEvaluation evaluation = NiveauEvaluationDto.ToEntity(niveauEvaluationDto);
            niveauEvaluationList.add(evaluation);
        }
        competence.setNiveauEvaluations(niveauEvaluationList);
        return competence;
        //return modelMapper.map(competenceDto,Competence.class);
    }
}
