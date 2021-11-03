package com.odc.suiviapprenants.dto;


import com.odc.suiviapprenants.model.Ressource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RessourcesDto {
    private Long id;
    private String titre;
    private String url;
    private byte[] pieceJointe;

    public static RessourcesDto fromEntity(Ressource ressource){
        if(ressource == null) return null;
        return RessourcesDto.builder()
                .id(ressource.getId())
                .titre(ressource.getTitre())
                .url(ressource.getUrl())
                .pieceJointe(ressource.getPieceJointe())
                .build();
    }

    public static Ressource toEntity(RessourcesDto ressourcesDto){
        if (ressourcesDto == null) return null;
        Ressource ressource = new Ressource();
        ressource.setTitre(ressourcesDto.getTitre());
        ressource.setUrl(ressourcesDto.getUrl());
        ressource.setPieceJointe(ressourcesDto.getPieceJointe());
        return ressource;
    }
}
