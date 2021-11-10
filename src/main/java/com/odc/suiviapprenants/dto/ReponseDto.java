package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Reponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ReponseDto {

    Long id;
    String libelle;
    byte[] pieceJointe;
    int note;

    @JsonIgnore
    MessageDto message;
    @JsonIgnore
    ApprenantDto apprenant;
    @JsonIgnore
    FormateurDto formateur;

    public static ReponseDto fromEntity(Reponse reponse){
        if (reponse == null) return null;

        return ReponseDto.builder()
                .id(reponse.getId())
                .libelle(reponse.getLibelle())
                .note(reponse.getNote())
                .pieceJointe(reponse.getPieceJointe())
                .build();

    }


    public static Reponse toEntity(ReponseDto dto)
    {
        if(dto==null) return null;

        Reponse reponse = new Reponse();
        reponse.setId(dto.getId());
        reponse.setLibelle(dto.getLibelle());
        reponse.setPieceJointe(dto.getPieceJointe());
        reponse.setNote(dto.getNote());
        reponse.setMessage(MessageDto.toEntity(dto.getMessage()));
        reponse.setApprenant(ApprenantDto.toEntity(dto.getApprenant()));
        reponse.setFormateur(FormateurDto.toEntity(dto.getFormateur()));

        return reponse;
    }
}
