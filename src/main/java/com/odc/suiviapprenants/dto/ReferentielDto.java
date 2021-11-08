package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Referentiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

import javax.persistence.Lob;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ReferentielDto {
    @Id
    private Long id;

    private String libelle;

    private String description;

    private String critereAdmission;

    private String critereEvaluation;

    @Lob
    private byte[] programme;

    private List<GroupeCompetenceDto> groupeCompetences;

    public  static ReferentielDto fromEntity(Referentiel referentiel){

        if (referentiel == null){
            return null;
        }

        return ReferentielDto.builder()
                .id(referentiel.getId())
                .libelle(referentiel.getLibelle())
                .description(referentiel.getDescription())
                .critereAdmission(referentiel.getCritereAdmission())
                .critereEvaluation(referentiel.getCritereEvaluation())
                .programme(referentiel.getProgramme())
                .groupeCompetences(
                        referentiel.getGroupeCompetences() != null ?
                                referentiel.getGroupeCompetences().stream()
                                        .map(GroupeCompetenceDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Referentiel toEntity(ReferentielDto referentielDto){
        if (referentielDto == null) return null;

        Referentiel referentiel = new Referentiel();
        referentiel.setLibelle(referentielDto.getLibelle());
        referentiel.setId(referentielDto.getId());
        referentiel.setCritereAdmission(referentielDto.getCritereAdmission());
        referentiel.setCritereEvaluation(referentielDto.getCritereEvaluation());
        referentiel.setDescription(referentielDto.getDescription());
        referentiel.setProgramme(referentielDto.getProgramme());
        if (referentielDto.getGroupeCompetences() != null)
            referentiel.setGroupeCompetences(referentielDto.getGroupeCompetences().stream().map(GroupeCompetenceDto::toEntity).collect(Collectors.toList()));

        return referentiel;
    }
}
