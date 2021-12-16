package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.FilDiscussion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class FilDeDiscutionDto {
    Long id;
    String titre;
    Collection<MessageDto> messages;
    @JsonIgnore
    ApprenantDto apprenant;

    @JsonIgnore
    BriefApprenantDto briefApprenant;

    public static FilDeDiscutionDto fromEntity(FilDiscussion filDiscussion){
        if (filDiscussion == null) return null;
        return FilDeDiscutionDto.builder()
                .id(filDiscussion.getId())
                .titre(filDiscussion.getTitre())
                .messages(
                      filDiscussion.getMessages() != null ?
                      filDiscussion.getMessages().stream()
                              .map(MessageDto::fromEntity)
                              .collect(Collectors.toList()) : new ArrayList<>()
                )
        .build();
    }

    public static FilDiscussion toEntity(FilDeDiscutionDto filDeDiscutionDto){
        if (filDeDiscutionDto == null) return null;
        FilDiscussion filDiscussion = new FilDiscussion();
        filDiscussion.setId(filDeDiscutionDto.getId());
        filDiscussion.setTitre(filDeDiscutionDto.getTitre());
        if (filDeDiscutionDto.getMessages() != null)
        filDiscussion.setMessages(filDeDiscutionDto.getMessages().stream().map(MessageDto::toEntity).collect(Collectors.toList()));
        filDiscussion.setApprenant(ApprenantDto.toEntity(filDeDiscutionDto.getApprenant()));
        filDiscussion.setBriefApprenant(BriefApprenantDto.toEntity(filDeDiscutionDto.getBriefApprenant()));
        return filDiscussion;
    }
}
