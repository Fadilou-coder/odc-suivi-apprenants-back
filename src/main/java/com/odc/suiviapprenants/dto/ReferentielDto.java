package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Referentiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Lob;

@Data
@Builder
@AllArgsConstructor
public class ReferentielDto {

    private Long id;

    private String libelle;

    private String description;

    private String critereAdmission;

    private String critereEvaluation;

    @Lob
    private byte[] programme;

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

        return referentiel;
    }


}
