package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class MessageDto {

    Long id;
    private String libelle;
    private byte[] pieceJointe;
    @JsonIgnore
    FilDeDiscutionDto filDeDiscution;

    private LocalDate creationDate;

    ApprenantDto apprenant;

    FormateurDto formateur;

    public static MessageDto fromEntity(Message message){

        if (message == null) return null;
        return MessageDto.builder()
                .id(message.getId())
                .libelle(message.getLibelle())
                .pieceJointe(message.getPieceJointe())
                .apprenant(
                        message.getApprenant() == null ? null:
                            ApprenantDto.fromEntity(message.getApprenant())
                )
                .formateur(
                        message.getFormateur() == null ? null:
                                FormateurDto.fromEntity(message.getFormateur())
                )
                .creationDate(LocalDate.from(message.getCreationDate()))
                .build();

    }

    public static Message toEntity(MessageDto messageDto){
        if (messageDto == null) return null;
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setLibelle(messageDto.getLibelle());
        message.setPieceJointe(messageDto.getPieceJointe());
        message.setFilDiscussion(FilDeDiscutionDto.toEntity(messageDto.getFilDeDiscution()));
        if (messageDto.getApprenant() != null)
            message.setApprenant(ApprenantDto.toEntity(messageDto.getApprenant()));
        if (messageDto.getFormateur() != null)
            message.setFormateur(FormateurDto.toEntity(messageDto.getFormateur()));

        return message;
    }
}
