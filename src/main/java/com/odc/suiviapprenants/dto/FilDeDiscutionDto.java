package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.FilDiscussion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class FilDeDiscutionDto {
    Long id;
    private String titre;
    private Collection<MessageDto> messages;

    public static FilDeDiscutionDto fromEntity(FilDiscussion filDiscussion){
        if (filDiscussion == null) return null;
        return FilDeDiscutionDto.builder()
                .id(filDiscussion.getId())
                .titre(filDiscussion.getTitre())
                .messages(
                      filDiscussion.getMessages() != null ?
                      filDiscussion.getMessages().stream()
                              .map(MessageDto::fromEntity)
                              .collect(Collectors.toList()) : null
                )
        .build();
    }

    public static FilDiscussion toEntity(FilDeDiscutionDto filDeDiscutionDto){
        if (filDeDiscutionDto == null) return null;
        FilDiscussion filDiscussion = new FilDiscussion();
        filDiscussion.setTitre(filDeDiscutionDto.getTitre());
        filDiscussion.setMessages(filDeDiscutionDto.getMessages().stream().map(MessageDto::toEntity).collect(Collectors.toList()));
        return filDiscussion;
    }
}
