package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.Referentiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferentielDto {
    @Id
    private Long id;

    private String libelle;

    private String description;

    private String critereAdmission;

    private String critereEvaluation;

    protected byte[] programme;

    public static ReferentielDto mapFromEntity(Referentiel referentiel)
    {
        //ModelMapper modelMapper = new ModelMapper();
        if(referentiel == null){
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

       // return modelMapper.map(referentiel,ReferentielDto.class);
    }

    public static  Referentiel mapToEntity(ReferentielDto referentielDto)
    {
      //  ModelMapper modelMapper = new ModelMapper();
        if(referentielDto == null){
            return null;
        }
        Referentiel referentiel = new Referentiel();
        referentiel.setId(referentielDto.getId());
        referentiel.setLibelle(referentielDto.getLibelle());
        referentiel.setDescription(referentielDto.getDescription());
        referentiel.setCritereAdmission(referentielDto.getCritereAdmission());
        referentiel.setCritereEvaluation(referentiel.getCritereEvaluation());
        referentiel.setProgramme(referentielDto.getProgramme());
        return referentiel;
       // return modelMapper.map(referentielDto,Referentiel.class);
    }
}
