package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Competence;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CompetenceDto {
    private String libelle;

    private ModelMapper modelMapper;

    private CompetenceDto mapToEntity(Competence competence)
    {
        if(competence == null){
            return null;
        }

        return modelMapper.map(competence,CompetenceDto.class);
    }

    private Competence mapToEntity(CompetenceDto competenceDto)
    {
        if(competenceDto == null){
            return null;
        }
        return modelMapper.map(competenceDto,Competence.class);
    }
}
