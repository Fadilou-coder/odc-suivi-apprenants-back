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
    boolean niveau1 = false;
    boolean niveau2 = false;
    boolean niveau3 = false;

    private CompetenceDto competence;

    private ApprenantDto apprenant;

    public static CompetenceValideDto fromEntity(CompetenceValide competenceValide){
        if (competenceValide == null) return null;
        return CompetenceValideDto.builder()
                .id(competenceValide.getId())
                .niveau1(competenceValide.isNiveau1())
                .niveau2(competenceValide.isNiveau2())
                .niveau3(competenceValide.isNiveau3())
                .competence(CompetenceDto.fromEntity(competenceValide.getCompetence()))
                .apprenant(ApprenantDto.fromEntity(competenceValide.getApprenant()))
                .build();
    }

    public static CompetenceValide toEntity(CompetenceValideDto competenceValideDto){
        if (competenceValideDto == null) return null;
        CompetenceValide competenceValide = new CompetenceValide();
        competenceValide.setId(competenceValideDto.getId());
        competenceValide.setNiveau1(competenceValideDto.isNiveau1());
        competenceValide.setNiveau2(competenceValideDto.isNiveau2());
        competenceValide.setNiveau3(competenceValideDto.isNiveau3());
        competenceValide.setCompetence(CompetenceDto.toEntity(competenceValideDto.getCompetence()));
        competenceValide.setApprenant(ApprenantDto.toEntity(competenceValideDto.getApprenant()));

        return  competenceValide;
    }
}
