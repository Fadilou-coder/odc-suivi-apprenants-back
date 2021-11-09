package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.FilDiscussion;
import com.odc.suiviapprenants.model.Message;
import com.odc.suiviapprenants.model.Reponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class MessageDto {

    Long id;
    private String libelle;
    private byte[] pieceJointe;
    private Collection<ReponseDto> reponses;

    public static MessageDto fromEntity(Message message){

        if (message == null) return null;
        return MessageDto.builder()
                .id(message.getId())
                .libelle(message.getLibelle())
                .pieceJointe(message.getPieceJointe())
                .reponses(
                        message.getReponses() != null ?
                                message.getReponses().stream()
                                        .map(ReponseDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();

    }

    public static Message toEntity(MessageDto messageDto){
        if (messageDto == null) return null;
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setLibelle(messageDto.getLibelle());
        message.setPieceJointe(messageDto.getPieceJointe());
        if (messageDto.getReponses() != null)
        message.setReponses(messageDto.getReponses().stream().map(ReponseDto::toEntity).collect(Collectors.toList()));

        return message;
    }
}
