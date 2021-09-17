package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Competence;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompetenceDto {
    private Long id;
    private String libelle;

    public static CompetenceDto fromEntity(Competence competence) {
        if(competence == null) return null;

        return CompetenceDto.builder()
                .id(competence.getId())
                .libelle(competence.getLibelle())
                .build();
    }

    public static Competence toEntity(CompetenceDto competenceDto) {
        if(competenceDto == null) return null;

        Competence competence = new Competence();
        competence.setId(competenceDto.getId());
        competence.setLibelle(competenceDto.getLibelle());

        return competence;
    }
}
