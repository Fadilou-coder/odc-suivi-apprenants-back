package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Reponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ReponseDto {

    Long id;
    private String libelle;
    private byte[] pieceJointe;
    private int note;

    public static ReponseDto fromEntity(Reponse reponse){
        if (reponse == null) return null;

        return ReponseDto.builder()
                .id(reponse.getId())
                .libelle(reponse.getLibelle())
                .note(reponse.getNote())
                .pieceJointe(reponse.getPieceJointe())
                .build();

    }


    public Reponse toEntity(ReponseDto dto)
    {
        if(dto==null) return null;

        Reponse reponse = new Reponse();
        reponse.setId(dto.getId());
        reponse.setLibelle(dto.getLibelle());
        reponse.setPieceJointe(dto.getPieceJointe());
        reponse.setNote(dto.getNote());

        return reponse;
    }
}
